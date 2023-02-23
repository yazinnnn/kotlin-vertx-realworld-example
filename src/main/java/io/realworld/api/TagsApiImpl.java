package io.realworld.api;

import io.realworld.model.GetTags200Response;

import io.vertx.core.Future;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class TagsApiImpl implements TagsApi {
    public Future<ApiResponse<GetTags200Response>> getTags() {
        return Future.failedFuture(new HttpException(501));
    }

}
