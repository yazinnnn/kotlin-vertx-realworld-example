package io.realworld.extensions

import io.vertx.core.json.JsonObject

inline fun <reified T> JsonObject.mapTo(): T = mapTo(T::class.java)
