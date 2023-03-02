package io.realworld.verticle

import io.realworld.codec.registerIdentityCodec
import io.realworld.entity.BaseEntity
import io.vertx.core.json.JsonObject
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import org.testcontainers.containers.PostgreSQLContainer

class InitializerVerticle : CoroutineVerticle() {
  override suspend fun start() {
    registerCodec()
    testContainer()
  }

  private fun registerCodec(){
    vertx.eventBus().registerIdentityCodec<BaseEntity>()
  }

  private suspend fun testContainer() {
    val database = JsonPointer.from("/data/postgres").queryJson(config) as JsonObject
    if (database.getBoolean("test-container")) {
      vertx.executeBlocking {
        PostgreSQLContainer("postgres:11-alpine")
          .withDatabaseName(database.getString("db"))
          .withUsername(database.getString("username"))
          .withPassword(database.getString("password"))
          .withExposedPorts(database.getInteger("port")).start()
        it.complete(Unit)
      }.await()
    }
  }
}
