package io.realworld.api;

import io.realworld.model.CreateArticle201Response;
import io.realworld.model.CreateArticleRequest;
import io.realworld.model.GetArticlesFeed200Response;
import io.realworld.model.UpdateArticleRequest;

import io.vertx.core.Future;

public interface ArticlesApi  {
    Future<ApiResponse<CreateArticle201Response>> createArticle(CreateArticleRequest article);
    Future<ApiResponse<Void>> deleteArticle(String slug);
    Future<ApiResponse<CreateArticle201Response>> getArticle(String slug);
    Future<ApiResponse<GetArticlesFeed200Response>> getArticles(String tag, String author, String favorited, Integer offset, Integer limit);
    Future<ApiResponse<GetArticlesFeed200Response>> getArticlesFeed(Integer offset, Integer limit);
    Future<ApiResponse<CreateArticle201Response>> updateArticle(String slug, UpdateArticleRequest article);
}
