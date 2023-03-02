package io.realworld.verticle

import io.realworld.service.UserService
import io.realworld.service.UserServiceImpl
import io.vertx.core.Promise
import io.vertx.core.json.JsonObject
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.serviceproxy.ServiceBinder
import org.hibernate.reactive.mutiny.Mutiny
import javax.persistence.Persistence

class DatabaseVerticle(private val jwt: JWTAuth) : CoroutineVerticle() {
  private lateinit var emf: Mutiny.SessionFactory

  override suspend fun start() {
    emf = vertx.executeBlocking(this::createFactory).await()
    val userService = UserServiceImpl(emf, jwt)
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


}
