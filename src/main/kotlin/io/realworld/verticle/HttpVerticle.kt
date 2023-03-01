package io.realworld.verticle

import io.realworld.api.*
import io.realworld.handler.ArticlesApiHandler
import io.vertx.core.Future
import io.vertx.ext.auth.PubSecKeyOptions
import io.vertx.ext.auth.authentication.TokenCredentials
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.APIKeyHandler
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await

class HttpVerticle : CoroutineVerticle() {
  private val specFile = "openapi.yml"


  private val articlesHandler = ArticlesApiHandler(TODO())
  private val commentsHandler = CommentsApiHandler(TODO())
  private val favoritesHandler = FavoritesApiHandler(TODO())
  private val profileHandler = ProfileApiHandler(TODO())
  private val tagsHandler = TagsApiHandler(TODO())
  private val userAndAuthenticationHandler = UserAndAuthenticationApiHandler(TODO())

  override suspend fun start() {
    vertx.createHttpServer()
      .requestHandler(router())
      .listen(8888).await()
  }

  private suspend fun router(): Router {
    val router = Router.router(vertx)
    router.route("/api/*").subRouter(openapi())
    router.errorHandler(400) {
      it.response()
        .putHeader("content-type", "text/html")
        .setStatusCode(422)
        .end("Bad Request : " + it.failure().message)
    }
    router.errorHandler(401) {
      it.response()
        .putHeader("content-type", "text/html")
        .setStatusCode(401)
        .end()
    }
    return router
  }


  private suspend fun openapi(): Router {
    val privateKey = vertx.fileSystem().readFile("private-key.pem").await()
    val publicKey = vertx.fileSystem().readFile("public.pem").await()
    val jwtAuthOptions = JWTAuthOptions()
      .apply {
        addPubSecKey(PubSecKeyOptions().setAlgorithm("RS256").setBuffer(privateKey))
        addPubSecKey(PubSecKeyOptions().setAlgorithm("RS256").setBuffer(publicKey))
        jwtOptions.algorithm = "RS256"
      }

    val jwt = JWTAuth.create(vertx, jwtAuthOptions)
    val builder = RouterBuilder.create(vertx, specFile).await()
    builder.securityHandler("Token")
      .bind { conf ->
        val apiKey = APIKeyHandler.create { cred, handler ->
          val credentials = TokenCredentials(cred)
          credentials.token = credentials.token.removePrefix("Token ")
          jwt.authenticate(credentials, handler)
        }.header(conf.getString("name"))
        Future.succeededFuture(apiKey)
      }
    builder.options.isRequireSecurityHandlers = true
    tagsHandler.mount(builder)
    userAndAuthenticationHandler.mount(builder)
    return builder.createRouter()
  }
}


