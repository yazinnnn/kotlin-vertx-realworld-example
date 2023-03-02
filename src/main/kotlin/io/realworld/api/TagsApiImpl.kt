package io.realworld.api

import io.realworld.model.response.ApiResponse
import io.realworld.model.response.GetTags200Response
import io.vertx.core.Future
import io.vertx.ext.web.handler.HttpException

class TagsApiImpl : TagsApi {
  override val tags: Future<ApiResponse<GetTags200Response>>
    get() = Future.failedFuture(HttpException(501))
}
