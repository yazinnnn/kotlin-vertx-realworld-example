package io.realworld.verticle

import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.realworld.codec.registerIdentityCodec
import io.realworld.entity.BaseEntity
import io.vertx.core.json.JsonObject
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import org.testcontainers.containers.PostgreSQLContainer

class InitializerVerticle : CoroutineVerticle() {
  override suspend fun start() {
    registerCodec()
    testContainer()
  }

  private fun registerCodec() {
    val kotlin = KotlinModule.Builder()
      .withReflectionCacheSize(512)
      .configure(KotlinFeature.NullToEmptyCollection, false)
      .configure(KotlinFeature.NullToEmptyMap, false)
      .configure(KotlinFeature.NullIsSameAsDefault, false)
      .configure(KotlinFeature.SingletonSupport, false)
      .configure(KotlinFeature.StrictNullChecks, false)
      .build()
    DatabindCodec.mapper().registerModule(kotlin)
    DatabindCodec.prettyMapper().registerModule(kotlin)
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
