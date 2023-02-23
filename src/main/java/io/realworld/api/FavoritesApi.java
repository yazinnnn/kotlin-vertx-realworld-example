package io.realworld.api;

import io.realworld.model.CreateArticle201Response;

import io.vertx.core.Future;

public interface FavoritesApi  {
    Future<ApiResponse<CreateArticle201Response>> createArticleFavorite(String slug);
    Future<ApiResponse<CreateArticle201Response>> deleteArticleFavorite(String slug);
}
