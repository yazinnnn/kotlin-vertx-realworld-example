package io.realworld.api

import io.realworld.model.response.ApiResponse
import io.realworld.model.response.CreateArticle201Response
import io.vertx.core.Future

interface FavoritesApi {
  fun createArticleFavorite(slug: String): Future<ApiResponse<CreateArticle201Response>>
  fun deleteArticleFavorite(slug: String): Future<ApiResponse<CreateArticle201Response>>
}
