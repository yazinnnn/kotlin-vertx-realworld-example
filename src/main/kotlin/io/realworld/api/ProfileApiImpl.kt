package io.realworld.api

import io.realworld.model.response.ApiResponse
import io.realworld.model.response.GetProfileByUsername200Response
import io.vertx.core.Future
import io.vertx.ext.web.handler.HttpException

class ProfileApiImpl : ProfileApi {
  override fun followUserByUsername(username: String): Future<ApiResponse<GetProfileByUsername200Response>> {
    return Future.failedFuture(HttpException(501))
  }

  override fun getProfileByUsername(username: String): Future<ApiResponse<GetProfileByUsername200Response>> {
    return Future.failedFuture(HttpException(501))
  }

  override fun unfollowUserByUsername(username: String): Future<ApiResponse<GetProfileByUsername200Response>> {
    return Future.failedFuture(HttpException(501))
  }
}
