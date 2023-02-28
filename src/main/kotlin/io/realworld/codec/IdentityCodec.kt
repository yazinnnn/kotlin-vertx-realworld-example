package io.realworld.codec

import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.MessageCodec

class IdentityCodec<T>(private val clazz: Class<T>) : MessageCodec<T, T> {
  override fun encodeToWire(buffer: Buffer?, s: T) {
  }

  override fun decodeFromWire(pos: Int, buffer: Buffer?): T? {
    return null
  }

  override fun transform(s: T): T {
    return s
  }

  override fun name(): String {
    return clazz.name + "Codec"
  }

  override fun systemCodecID(): Byte {
    return -1
  }
}

/**
 * register a message identity codec, only available in local eventbus;
 */
inline fun <reified T> EventBus.registerIdentityCodec(): EventBus =
  registerDefaultCodec(T::class.java, IdentityCodec(T::class.java))
