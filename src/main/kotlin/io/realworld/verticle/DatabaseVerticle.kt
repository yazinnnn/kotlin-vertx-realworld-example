package io.realworld.verticle

import io.realworld.service.UserService
import io.realworld.service.UserServiceImpl
import io.vertx.core.Promise
import io.vertx.core.json.JsonObject
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.ext.auth.PubSecKeyOptions
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.serviceproxy.ServiceBinder
import org.hibernate.reactive.mutiny.Mutiny
import org.testcontainers.shaded.org.bouncycastle.asn1.x509.ObjectDigestInfo.publicKey
import javax.persistence.Persistence

class DatabaseVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val jwt = createJwt()
    val emf = vertx.executeBlocking(this::createFactory).await()
    val userService = UserServiceImpl(emf, jwt,vertx)
    ServiceBinder(vertx).setAddress("UserServiceImpl").register(UserService::class.java, userService)
  }

  private fun createFactory(promise: Promise<Mutiny.SessionFactory>) {
    val postgres = JsonPointer.from("/data/postgres").queryJson(config) as JsonObject
    val props = mapOf(
      "javax.persistence.jdbc.url" to postgres.getString("jdbc-url")
    )
    val emf = Persistence
      .createEntityManagerFactory("postgresql", props)
      .unwrap(Mutiny.SessionFactory::class.java)
    promise.complete(emf)
  }

  private suspend fun createJwt(): JWTAuth {
    val privateKey = vertx.fileSystem().readFile("private-key.pem").await()
    val jwtAuthOptions = JWTAuthOptions()
      .apply {
        addPubSecKey(PubSecKeyOptions().setAlgorithm("RS256").setBuffer(privateKey))
        jwtOptions.algorithm = "RS256"
        jwtOptions.setExpiresInMinutes(60)
      }
    return JWTAuth.create(vertx, jwtAuthOptions)
  }

}
