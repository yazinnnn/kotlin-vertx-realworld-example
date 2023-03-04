package io.realworld.verticle

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.realworld.codec.registerIdentityCodec
import io.realworld.entity.BaseEntity
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.kotlin.coroutines.CoroutineVerticle

class InitializerVerticle : CoroutineVerticle() {
  override suspend fun start() {
    registerCodec()
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
    DatabindCodec.mapper()
      .registerModule(kotlin)
      .registerModule(JavaTimeModule())
    DatabindCodec.prettyMapper()
      .registerModule(kotlin)
      .registerModule(JavaTimeModule())
    vertx.eventBus().registerIdentityCodec<BaseEntity>()
  }
}
