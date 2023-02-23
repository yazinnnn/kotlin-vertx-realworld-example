package io.realworld.api;

import io.realworld.model.CreateArticleComment200Response;
import io.realworld.model.CreateArticleCommentRequest;
import io.realworld.model.GetArticleComments200Response;

import io.vertx.core.Future;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class CommentsApiImpl implements CommentsApi {
    public Future<ApiResponse<CreateArticleComment200Response>> createArticleComment(String slug, CreateArticleCommentRequest comment) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<Void>> deleteArticleComment(String slug, Integer id) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<GetArticleComments200Response>> getArticleComments(String slug) {
        return Future.failedFuture(new HttpException(501));
    }

}
