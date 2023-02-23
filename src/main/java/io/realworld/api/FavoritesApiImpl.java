package io.realworld.api;

import io.realworld.model.CreateArticle201Response;

import io.vertx.core.Future;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class FavoritesApiImpl implements FavoritesApi {
    public Future<ApiResponse<CreateArticle201Response>> createArticleFavorite(String slug) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<CreateArticle201Response>> deleteArticleFavorite(String slug) {
        return Future.failedFuture(new HttpException(501));
    }

}
