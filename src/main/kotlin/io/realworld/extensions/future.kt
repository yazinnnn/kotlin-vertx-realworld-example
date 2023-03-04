package io.realworld.extensions

import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.asUni
import io.smallrye.mutiny.vertx.UniHelper
import io.vertx.core.Future
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async

inline fun <reified T> Uni<T>.asFuture(): Future<T> {
  return UniHelper.toFuture(this)
}

context(CoroutineScope)
@OptIn(ExperimentalCoroutinesApi::class)
inline fun <P, R> coroutine(crossinline block: suspend (P) -> R): (P) -> Uni<R> {
  return { async { block(it) }.asUni() }
}
