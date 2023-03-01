package io.realworld.extensions

import io.realworld.model.response.ApiResponse
import io.vertx.core.Future
import io.vertx.ext.web.RoutingContext

inline fun <reified T> Future<ApiResponse<T>>.handleResponse(rc: RoutingContext) {
  onSuccess { response ->
    rc.response().statusCode = response.statusCode
    response.data?.let { rc.json(response.data) } ?: rc.end()
  }
  onFailure(rc::fail)
}
