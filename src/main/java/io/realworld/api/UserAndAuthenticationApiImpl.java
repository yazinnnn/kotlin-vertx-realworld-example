package io.realworld.api;

import io.realworld.model.*;
import io.realworld.model.request.CreateUser;
import io.realworld.model.request.Login;
import io.realworld.model.request.UpdateUser;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class UserAndAuthenticationApiImpl implements UserAndAuthenticationApi {
  public Future<ApiResponse<Login200Response>> createUser(CreateUser body) {
    return Future.failedFuture(new HttpException(501));
  }

  public Future<ApiResponse<Login200Response>> getCurrentUser(RoutingContext rc) {
    rc.user().attributes();
    var user = new Login200Response(new User("email", rc.request().getHeader("Authorization"), "user", "bio", "image"));
    return Future.succeededFuture(new ApiResponse<>(user));
  }

  public Future<ApiResponse<Login200Response>> login(Login body) {
    return Future.failedFuture(new HttpException(501));
  }

  public Future<ApiResponse<Login200Response>> updateCurrentUser(UpdateUser body) {
    return Future.failedFuture(new HttpException(501));
  }

}
