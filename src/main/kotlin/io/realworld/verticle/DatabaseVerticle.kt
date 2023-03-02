package io.realworld.verticle

import io.vertx.core.Promise
import io.vertx.core.json.JsonObject
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import org.hibernate.reactive.mutiny.Mutiny
import javax.persistence.Persistence

class DatabaseVerticle : CoroutineVerticle() {
  private lateinit var emf: Mutiny.SessionFactory

  override suspend fun start() {
    emf = vertx.executeBlocking(this::createFactory).await()
  }

  private fun createFactory(promise: Promise<Mutiny.SessionFactory>) {
    val postgres = JsonPointer.from("/postgres").queryJson(config) as JsonObject
    val props = mapOf(
      "javax.persistence.jdbc.url" to postgres.getString("jdbc-url")
    )
    val emf = Persistence
      .createEntityManagerFactory("postgresql", props).unwrap(Mutiny.SessionFactory::class.java)
    promise.complete(emf)
  }
}
