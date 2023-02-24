package io.realworld

import io.realworld.api.*
import io.vertx.core.Future
import io.vertx.core.Launcher
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.APIKeyHandler
import io.vertx.ext.web.handler.JWTAuthHandler
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import java.util.*
import java.util.stream.Collectors

class MainVerticle : CoroutineVerticle() {
  private val specFile = "src/main/resources/openapi.yml"


  private val articlesHandler = ArticlesApiHandler(ArticlesApiImpl())
  private val commentsHandler = CommentsApiHandler(CommentsApiImpl())
  private val favoritesHandler = FavoritesApiHandler(FavoritesApiImpl())
  private val profileHandler = ProfileApiHandler(ProfileApiImpl())
  private val tagsHandler = TagsApiHandler(TagsApiImpl())
  private val userAndAuthenticationHandler = UserAndAuthenticationApiHandler(UserAndAuthenticationApiImpl())


  override suspend fun start() {
//    val router = Router.router(vertx)
//    val jwtAuth = JWTAuth.create(vertx, JWTAuthOptions())
//    router.route().handler(JWTAuthHandler.create(jwtAuth, "Token"))

    vertx.createHttpServer()
      .requestHandler(router())
      .listen(8888).await()
  }

  private suspend fun router(): Router {
    val router = Router.router(vertx)
    router.route("/api/*").subRouter(openapi())
    router.errorHandler(400, this::validationFailureHandler)
    return router
  }

  private suspend fun openapi(): Router {

    val jwt = JWTAuth.create(vertx, JWTAuthOptions())
    val builder = RouterBuilder.create(vertx, specFile).await()
    val jwtAuthHandler = JWTAuthHandler.create(jwt, "Token")
    builder.securityHandler("Token")
      .bind { Future.succeededFuture(jwtAuthHandler) }
    builder.options.isRequireSecurityHandlers = false
    tagsHandler.mount(builder)
    userAndAuthenticationHandler.mount(builder)
    return builder.createRouter()
  }

  private fun validationFailureHandler(rc: RoutingContext) {
    println(rc.failure().localizedMessage)
    println(rc.failure().javaClass)
    println(rc.failure().cause?.localizedMessage)
    rc.failure().printStackTrace()
    rc.response()
      .putHeader("content-type", "text/html")
      .setStatusCode(422)
      .end("Bad Request : " + rc.failure().message)
  }
}

fun main() {
  Launcher.main(arrayOf("run", MainVerticle::class.qualifiedName))
}
