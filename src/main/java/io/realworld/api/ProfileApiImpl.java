package io.realworld.api;

import io.realworld.model.GetProfileByUsername200Response;

import io.vertx.core.Future;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class ProfileApiImpl implements ProfileApi {
    public Future<ApiResponse<GetProfileByUsername200Response>> followUserByUsername(String username) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<GetProfileByUsername200Response>> getProfileByUsername(String username) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<GetProfileByUsername200Response>> unfollowUserByUsername(String username) {
        return Future.failedFuture(new HttpException(501));
    }

}
