package io.realworld.api;

import io.realworld.model.GetTags200Response;

import io.vertx.core.Future;

public interface TagsApi  {
    Future<ApiResponse<GetTags200Response>> getTags();
}
