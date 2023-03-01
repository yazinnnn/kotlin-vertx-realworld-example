package io.realworld.api

import io.realworld.model.request.CreateArticleRequest
import io.realworld.model.request.UpdateArticleRequest
import io.realworld.model.response.ApiResponse
import io.realworld.model.response.CreateArticle201Response
import io.realworld.model.response.GetArticlesFeed200Response
import io.vertx.core.Future

interface ArticlesApi {
  fun createArticle(article: CreateArticleRequest): Future<ApiResponse<CreateArticle201Response>>
  fun deleteArticle(slug: String): Future<ApiResponse<Void>>
  fun getArticle(slug: String): Future<ApiResponse<CreateArticle201Response>>
  fun getArticles(
    tag: String?,
    author: String?,
    favorited: String?,
    offset: Int,
    limit: Int
  ): Future<ApiResponse<GetArticlesFeed200Response>>

  fun getArticlesFeed(offset: Int, limit: Int): Future<ApiResponse<GetArticlesFeed200Response>>
  fun updateArticle(slug: String, article: UpdateArticleRequest): Future<ApiResponse<CreateArticle201Response>>
}
