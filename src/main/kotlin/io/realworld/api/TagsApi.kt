package io.realworld.api

import io.realworld.model.response.ApiResponse
import io.realworld.model.response.GetTags200Response
import io.vertx.core.Future

interface TagsApi {
  val tags: Future<ApiResponse<GetTags200Response>>
}
