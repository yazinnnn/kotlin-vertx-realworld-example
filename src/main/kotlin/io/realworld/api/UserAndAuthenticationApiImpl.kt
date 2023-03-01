package io.realworld.api

import io.realworld.model.request.CreateUserRequest
import io.realworld.model.request.LoginRequest
import io.realworld.model.request.UpdateCurrentUserRequest
import io.realworld.model.response.ApiResponse
import io.realworld.model.response.Login200Response
import io.vertx.core.Future
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.HttpException

class UserAndAuthenticationApiImpl:UserAndAuthenticationApi {
  override fun createUser(body: CreateUserRequest): Future<ApiResponse<Login200Response>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }

  override fun getCurrentUser(rc: RoutingContext): Future<ApiResponse<Login200Response>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }

  override fun login(body: LoginRequest): Future<ApiResponse<Login200Response>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }

  override fun updateCurrentUser(body: UpdateCurrentUserRequest): Future<ApiResponse<Login200Response>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }
}
