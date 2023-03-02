package io.realworld.extensions

import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.vertx.UniHelper
import io.vertx.core.Future

inline fun <reified T> Uni<T>.toFuture(): Future<T> {
  return UniHelper.toFuture(this)
}
