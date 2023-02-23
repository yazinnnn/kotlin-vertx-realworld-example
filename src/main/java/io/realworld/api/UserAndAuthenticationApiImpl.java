package io.realworld.api;

import io.realworld.model.CreateUserRequest;
import io.realworld.model.Login200Response;
import io.realworld.model.LoginRequest;
import io.realworld.model.UpdateCurrentUserRequest;

import io.vertx.core.Future;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class UserAndAuthenticationApiImpl implements UserAndAuthenticationApi {
    public Future<ApiResponse<Login200Response>> createUser(CreateUserRequest body) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<Login200Response>> getCurrentUser() {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<Login200Response>> login(LoginRequest body) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<Login200Response>> updateCurrentUser(UpdateCurrentUserRequest body) {
        return Future.failedFuture(new HttpException(501));
    }

}
