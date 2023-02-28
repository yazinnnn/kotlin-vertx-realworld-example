package io.realworld

import io.realworld.verticle.HttpVerticle
import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.core.Launcher
import io.vertx.kotlin.config.configStoreOptionsOf
import io.vertx.kotlin.core.deploymentOptionsOf
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val store =
      configStoreOptionsOf(type = "file", format = "yaml", config = jsonObjectOf("path" to "application.yml"))
    val retriever = ConfigRetriever.create(vertx, ConfigRetrieverOptions().addStore(store))
    val config = retriever.config.await()
    vertx.deployVerticle(
      HttpVerticle::class.qualifiedName,
      deploymentOptionsOf(config = config.getJsonObject("http"))
    ).await()
  }
}

fun main() {
  Launcher.main(arrayOf("run", MainVerticle::class.qualifiedName))
}
