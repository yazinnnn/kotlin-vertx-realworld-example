package io.realworld.api

import io.realworld.model.request.CreateArticleCommentRequest
import io.realworld.model.response.ApiResponse
import io.realworld.model.response.CreateArticleComment200Response
import io.realworld.model.response.GetArticleComments200Response
import io.vertx.core.Future

interface CommentsApi {
  fun createArticleComment(
    slug: String,
    comment: CreateArticleCommentRequest
  ): Future<ApiResponse<CreateArticleComment200Response>>

  fun deleteArticleComment(slug: String, id: Int): Future<ApiResponse<Void>>
  fun getArticleComments(slug: String): Future<ApiResponse<GetArticleComments200Response>>
}
