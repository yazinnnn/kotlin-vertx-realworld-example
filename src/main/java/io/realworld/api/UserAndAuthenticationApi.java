package io.realworld.api;

import io.realworld.model.CreateUserRequest;
import io.realworld.model.Login200Response;
import io.realworld.model.LoginRequest;
import io.realworld.model.UpdateCurrentUserRequest;

import io.vertx.core.Future;

public interface UserAndAuthenticationApi  {
    Future<ApiResponse<Login200Response>> createUser(CreateUserRequest body);
    Future<ApiResponse<Login200Response>> getCurrentUser();
    Future<ApiResponse<Login200Response>> login(LoginRequest body);
    Future<ApiResponse<Login200Response>> updateCurrentUser(UpdateCurrentUserRequest body);
}
