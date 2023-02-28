package io.realworld.api;

import io.realworld.model.Login200Response;

import io.realworld.model.request.CreateUser;
import io.realworld.model.request.Login;
import io.realworld.model.request.UpdateUser;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;

public interface UserAndAuthenticationApi  {
    Future<ApiResponse<Login200Response>> createUser(CreateUser body);
    Future<ApiResponse<Login200Response>> getCurrentUser(RoutingContext rc);
    Future<ApiResponse<Login200Response>> login(Login body);
    Future<ApiResponse<Login200Response>> updateCurrentUser(UpdateUser body);
}
