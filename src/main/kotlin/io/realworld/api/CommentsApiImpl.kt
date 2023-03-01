package io.realworld.api

import io.realworld.model.request.CreateArticleCommentRequest
import io.realworld.model.response.ApiResponse
import io.realworld.model.response.CreateArticleComment200Response
import io.realworld.model.response.GetArticleComments200Response
import io.vertx.core.Future
import io.vertx.ext.web.handler.HttpException

class CommentsApiImpl : CommentsApi {
  override fun createArticleComment(
    slug: String,
    comment: CreateArticleCommentRequest
  ): Future<ApiResponse<CreateArticleComment200Response>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }

  override fun deleteArticleComment(slug: String, id: Int): Future<ApiResponse<Void>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }

  override fun getArticleComments(slug: String): Future<ApiResponse<GetArticleComments200Response>> {
    return Future.failedFuture(HttpException(501, "未实现"))
  }
}
