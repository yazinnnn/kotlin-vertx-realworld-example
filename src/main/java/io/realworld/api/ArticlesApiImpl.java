package io.realworld.api;

import io.realworld.model.CreateArticle201Response;
import io.realworld.model.CreateArticleRequest;
import io.realworld.model.GetArticlesFeed200Response;
import io.realworld.model.UpdateArticleRequest;

import io.vertx.core.Future;
import io.vertx.ext.web.handler.HttpException;

// Implement this class

public class ArticlesApiImpl implements ArticlesApi {
    public Future<ApiResponse<CreateArticle201Response>> createArticle(CreateArticleRequest article) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<Void>> deleteArticle(String slug) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<CreateArticle201Response>> getArticle(String slug) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<GetArticlesFeed200Response>> getArticles(String tag, String author, String favorited, Integer offset, Integer limit) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<GetArticlesFeed200Response>> getArticlesFeed(Integer offset, Integer limit) {
        return Future.failedFuture(new HttpException(501));
    }

    public Future<ApiResponse<CreateArticle201Response>> updateArticle(String slug, UpdateArticleRequest article) {
        return Future.failedFuture(new HttpException(501));
    }

}
