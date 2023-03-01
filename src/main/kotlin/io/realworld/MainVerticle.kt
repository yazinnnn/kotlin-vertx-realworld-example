package io.realworld

import io.realworld.extensions.runVertx
import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.core.json.JsonObject
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.kotlin.config.configStoreOptionsOf
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import org.testcontainers.containers.PostgreSQLContainer

class MainVerticle : CoroutineVerticle() {
  private suspend fun initConfig() {
    val store = configStoreOptionsOf(type = "file", format = "yaml", config = jsonObjectOf("path" to "application.yml"))
    val retriever = ConfigRetriever.create(vertx, ConfigRetrieverOptions().addStore(store))
    config.mergeIn(retriever.config.await(), true)
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

  override suspend fun start() {
    initConfig()
    testContainer()
//    runVerticle<HttpVerticle>().await()
  }
}


fun main() {
  runVertx<MainVerticle>()
}
