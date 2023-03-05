package io.realworld.extensions

import io.realworld.model.response.ApiResponse
import io.vertx.core.Future
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory
import kotlin.math.log

inline fun <reified T> Future<ApiResponse<T>>.handleResponse(rc: RoutingContext) {
  onSuccess { response ->
    rc.response().statusCode = response.statusCode
    response.data?.let { rc.json(response.data) } ?: rc.end()
  }
  onFailure {
    logger.warn(it.toString())
    logger.warn(it.localizedMessage)
    logger.warn(it.cause.toString())
    logger.warn(it.cause?.localizedMessage)

    rc.fail(it)
  }
}

val logger = LoggerFactory.getLogger("future")
