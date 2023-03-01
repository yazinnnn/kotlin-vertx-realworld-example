package io.realworld.api

import io.realworld.model.response.ApiResponse
import io.realworld.model.response.GetProfileByUsername200Response
import io.vertx.core.Future

interface ProfileApi {
  fun followUserByUsername(username: String): Future<ApiResponse<GetProfileByUsername200Response>>
  fun getProfileByUsername(username: String): Future<ApiResponse<GetProfileByUsername200Response>>
  fun unfollowUserByUsername(username: String): Future<ApiResponse<GetProfileByUsername200Response>>
}
