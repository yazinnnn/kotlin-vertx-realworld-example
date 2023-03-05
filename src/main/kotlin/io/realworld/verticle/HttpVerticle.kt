package io.realworld.verticle

import io.realworld.api.ArticlesApiImpl
import io.realworld.api.CommentsApiImpl
import io.realworld.api.UserAndAuthenticationApiImpl
import io.realworld.handler.ArticlesApiHandler
import io.realworld.handler.CommentsApiHandler
import io.realworld.handler.UserAndAuthenticationApiHandler
import io.realworld.service.UserServiceVertxEBProxy
import io.vertx.core.Future
import io.vertx.ext.auth.PubSecKeyOptions
import io.vertx.ext.auth.authentication.TokenCredentials
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.APIKeyHandler
import io.vertx.ext.web.handler.HttpException
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await

class HttpVerticle : CoroutineVerticle() {
  private val specFile = "openapi.yml"


  private val articlesHandler = ArticlesApiHandler(ArticlesApiImpl())
  private val commentsHandler = CommentsApiHandler(CommentsApiImpl())

  //  private val favoritesHandler = FavoritesApiHandler(TODO())
//  private val profileHandler = ProfileApiHandler(TODO())
//  private val tagsHandler = TagsApiHandler(TODO())
  private val userAndAuthenticationHandler by lazy {
    UserAndAuthenticationApiHandler(
      UserAndAuthenticationApiImpl(
        UserServiceVertxEBProxy(vertx, "UserServiceImpl")
      )
    )
  }

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
        .putHeader("content-type", "application/json; charset=utf8")
        .setStatusCode(422)
        .end()
    }
    router.errorHandler(401) {
      it.response()
        .putHeader("content-type", "text/html")
        .setStatusCode(401)
        .end()
    }
    router.errorHandler(501) {
      it.response()
        .setStatusCode(501)
        .end()
    }
    router.errorHandler(500) {
      val cause = it.failure().cause
      if (cause is HttpException) {
        it.response()
          .setStatusCode(cause.statusCode)
          .end(cause.message)
      } else {
        it.response()
          .setStatusCode(500)
          .end(cause?.cause?.message ?: cause?.message ?: "")
      }
    }
    return router
  }


  private suspend fun openapi(): Router {
    val publicKey = vertx.fileSystem().readFile("public.pem").await()
    val jwtAuthOptions = JWTAuthOptions()
      .apply {
        addPubSecKey(PubSecKeyOptions().setAlgorithm("RS256").setBuffer(publicKey))
        jwtOptions.algorithm = "RS256"
        jwtOptions.setExpiresInMinutes(60)
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
    articlesHandler.mount(builder)
    commentsHandler.mount(builder)
    userAndAuthenticationHandler.mount(builder)
    return builder.createRouter()
  }
}


