package io.realworld.api

import io.realworld.model.request.CreateArticleRequest
import io.realworld.model.request.UpdateArticleRequest
import io.realworld.model.response.ApiResponse
import io.realworld.model.response.CreateArticle201Response
import io.realworld.model.response.GetArticlesFeed200Response
import io.vertx.core.Future
import io.vertx.ext.web.handler.HttpException

class ArticlesApiImpl:ArticlesApi {
  override fun createArticle(article: CreateArticleRequest): Future<ApiResponse<CreateArticle201Response>> {
    return Future.failedFuture(HttpException(501,"未实现"))
  }

  override fun deleteArticle(slug: String): Future<ApiResponse<Void>> {
    return Future.failedFuture(HttpException(501,"未实现"))
  }

  override fun getArticle(slug: String): Future<ApiResponse<CreateArticle201Response>> {
    return Future.failedFuture(HttpException(501,"未实现"))
  }

  override fun getArticles(
    tag: String?,
    author: String?,
    favorited: String?,
    offset: Int,
    limit: Int
  ): Future<ApiResponse<GetArticlesFeed200Response>> {
    return Future.failedFuture(HttpException(501,"未实现"))
  }

  override fun getArticlesFeed(offset: Int, limit: Int): Future<ApiResponse<GetArticlesFeed200Response>> {
    return Future.failedFuture(HttpException(501,"未实现"))
  }

  override fun updateArticle(
    slug: String,
    article: UpdateArticleRequest
  ): Future<ApiResponse<CreateArticle201Response>> {
    return Future.failedFuture(HttpException(501,"未实现"))
  }
}
