package io.realworld.verticle

import io.realworld.extensions.runVerticle
import io.vertx.ext.auth.PubSecKeyOptions
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await

class JwtVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val jwt = initJwt()
    runVerticle({ DatabaseVerticle(jwt) }) { it.config = config }.await()
    runVerticle({ HttpVerticle(jwt) }) { it.config = config }.await()
  }

  private suspend fun initJwt(): JWTAuth {
    val privateKey = vertx.fileSystem().readFile("private-key.pem").await()
    val publicKey = vertx.fileSystem().readFile("public.pem").await()
    val jwtAuthOptions = JWTAuthOptions()
      .apply {
        addPubSecKey(PubSecKeyOptions().setAlgorithm("RS256").setBuffer(privateKey))
        addPubSecKey(PubSecKeyOptions().setAlgorithm("RS256").setBuffer(publicKey))
        jwtOptions.algorithm = "RS256"
        jwtOptions.setExpiresInMinutes(60)
      }
    return JWTAuth.create(vertx, jwtAuthOptions)
  }
}
