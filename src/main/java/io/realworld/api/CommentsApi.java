package io.realworld.api;

import io.realworld.model.CreateArticleComment200Response;
import io.realworld.model.CreateArticleCommentRequest;
import io.realworld.model.GetArticleComments200Response;

import io.vertx.core.Future;

public interface CommentsApi  {
    Future<ApiResponse<CreateArticleComment200Response>> createArticleComment(String slug, CreateArticleCommentRequest comment);
    Future<ApiResponse<Void>> deleteArticleComment(String slug, Integer id);
    Future<ApiResponse<GetArticleComments200Response>> getArticleComments(String slug);
}
