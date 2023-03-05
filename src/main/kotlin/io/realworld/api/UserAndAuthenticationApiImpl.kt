package io.realworld.api

import io.realworld.extensions.mapTo
import io.realworld.model.request.CreateUserRequest
import io.realworld.model.request.LoginRequest
import io.realworld.model.request.UpdateCurrentUserRequest
import io.realworld.model.response.ApiResponse
import io.realworld.model.response.Login200Response
import io.realworld.service.UserService
import io.vertx.core.Future
import io.vertx.core.json.JsonObject

class UserAndAuthenticationApiImpl(
  private val userService: UserService
) : UserAndAuthenticationApi {
  override fun createUser(body: CreateUserRequest): Future<ApiResponse<Login200Response>> {
    return userService.createUser(JsonObject.mapFrom(body.user))
      .map { ApiResponse(data = it.mapTo()) }
  }

  override fun getCurrentUser(uid: Long): Future<ApiResponse<Login200Response>> {
    return userService.getCurrentUser(uid)
      .map { ApiResponse(data = it.mapTo()) }
  }

  override fun login(body: LoginRequest): Future<ApiResponse<Login200Response>> {
    return userService.login(body.user.email, body.user.password)
      .map<ApiResponse<Login200Response>?> { ApiResponse(data = it.mapTo()) }
      .onFailure {
        println(it.localizedMessage)
      }
  }

  override fun updateCurrentUser(uid: Long, body: UpdateCurrentUserRequest): Future<ApiResponse<Login200Response>> {
    return userService.updateUser(uid, JsonObject.mapFrom(body.user))
      .map { ApiResponse(data = it.mapTo()) }
  }
}
