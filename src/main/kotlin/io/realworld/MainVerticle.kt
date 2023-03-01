package io.realworld

import io.realworld.extensions.runVerticle
import io.realworld.extensions.runVertx
import io.realworld.verticle.HttpVerticle
import io.realworld.verticle.InitializerVerticle
import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.kotlin.config.configStoreOptionsOf
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await

class MainVerticle : CoroutineVerticle() {
  private suspend fun initConfig() {
    val store = configStoreOptionsOf(type = "file", format = "yaml", config = jsonObjectOf("path" to "application.yml"))
    val retriever = ConfigRetriever.create(vertx, ConfigRetrieverOptions().addStore(store))
    config.mergeIn(retriever.config.await(), true)
  }


  override suspend fun start() {
    initConfig()
    runVerticle<InitializerVerticle> { it.config = config }.await()
    runVerticle<HttpVerticle> { it.config = config }.await()
  }
}


fun main() {
  runVertx<MainVerticle>()
}
