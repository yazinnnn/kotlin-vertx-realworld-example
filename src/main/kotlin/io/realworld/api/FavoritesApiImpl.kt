package io.realworld.api

import io.realworld.model.response.ApiResponse
import io.realworld.model.response.CreateArticle201Response
import io.vertx.core.Future
import io.vertx.ext.web.handler.HttpException

class FavoritesApiImpl:FavoritesApi {
  override fun createArticleFavorite(slug: String): Future<ApiResponse<CreateArticle201Response>> {
    return Future.failedFuture(HttpException(501))
  }

  override fun deleteArticleFavorite(slug: String): Future<ApiResponse<CreateArticle201Response>> {
    return Future.failedFuture(HttpException(501))
  }
}
