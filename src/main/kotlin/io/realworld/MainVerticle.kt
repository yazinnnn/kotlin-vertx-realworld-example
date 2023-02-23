package io.realworld

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.realworld.api.*
import io.realworld.service.TestService
import io.realworld.service.TestServiceImpl
import io.realworld.service.TestServiceVertxEBProxy
import io.realworld.service.TestServiceVertxProxyHandler
import io.vertx.core.Launcher
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.ext.web.openapi.RouterBuilderOptions
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.serviceproxy.ServiceBinder
import io.vertx.serviceproxy.ServiceProxyBuilder
import org.slf4j.LoggerFactory

class MainVerticle : CoroutineVerticle() {
  private val specFile = "src/main/resources/openapi.yml"


  private val articlesHandler = ArticlesApiHandler(ArticlesApiImpl())
  private val commentsHandler = CommentsApiHandler(CommentsApiImpl())
  private val favoritesHandler = FavoritesApiHandler(FavoritesApiImpl())
  private val profileHandler = ProfileApiHandler(ProfileApiImpl())
  private val tagsHandler = TagsApiHandler(TagsApiImpl())
  private val userAndAuthenticationHandler = UserAndAuthenticationApiHandler(UserAndAuthenticationApiImpl())


  override suspend fun start() {
    val builder = RouterBuilder.create(vertx, specFile).await()
    builder.options = RouterBuilderOptions().setRequireSecurityHandlers(false)
//    articlesHandler.mount(builder)
//    commentsHandler.mount(builder)
//    favoritesHandler.mount(builder)
//    profileHandler.mount(builder)
    tagsHandler.mount(builder)
    userAndAuthenticationHandler.mount(builder)
    val router = builder.createRouter()
    router.errorHandler(400, this::validationFailureHandler)
    val base = Router.router(vertx)
    base.route("/api/*").subRouter(router)
    vertx.createHttpServer()
      .requestHandler(base)
      .listen(8888).await()
  }

  private suspend fun buildRouter() {
    val builder = RouterBuilder.create(vertx, specFile).await()
    builder.options = RouterBuilderOptions().setRequireSecurityHandlers(false)
//    articlesHandler.mount(builder)
//    commentsHandler.mount(builder)
//    favoritesHandler.mount(builder)
//    profileHandler.mount(builder)
    tagsHandler.mount(builder)
    userAndAuthenticationHandler.mount(builder)
    val router = builder.createRouter()
    router.errorHandler(400, this::validationFailureHandler)
    val base = Router.router(vertx)
    base.route("/api/*").subRouter(router)
  }

  private fun validationFailureHandler(rc: RoutingContext) {
    rc.response().setStatusCode(400)
      .end("Bad Request : " + rc.failure().message)
  }
}

fun main() {
  Launcher.main(arrayOf("run", MainVerticle::class.qualifiedName))
}
