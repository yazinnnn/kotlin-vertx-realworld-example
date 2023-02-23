package io.realworld.api;

import io.realworld.model.GetProfileByUsername200Response;

import io.vertx.core.Future;

public interface ProfileApi  {
    Future<ApiResponse<GetProfileByUsername200Response>> followUserByUsername(String username);
    Future<ApiResponse<GetProfileByUsername200Response>> getProfileByUsername(String username);
    Future<ApiResponse<GetProfileByUsername200Response>> unfollowUserByUsername(String username);
}
