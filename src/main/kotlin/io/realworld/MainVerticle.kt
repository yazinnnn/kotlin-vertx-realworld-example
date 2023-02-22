package io.realworld

import io.vertx.core.Future
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.ext.web.handler.JWTAuthHandler
import io.vertx.ext.web.openapi.OpenAPILoaderOptions
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val options = OpenAPILoaderOptions()

    val builder = RouterBuilder.create(vertx, "openapi.yml").await()
    val router = builder.securityHandler("Token")
      .bind { Future.succeededFuture(JWTAuthHandler.create(JWTAuth.create(vertx, JWTAuthOptions()))) }.await()
      .createRouter()
    val server = vertx.createHttpServer()
    server.requestHandler(router).listen(8888).await()
  }
}
