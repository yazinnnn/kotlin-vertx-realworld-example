package io.realworld.api;

import io.realworld.model.CreateArticleRequest;
import io.realworld.model.UpdateArticleRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticlesApiHandler {

    private static final Logger logger = LoggerFactory.getLogger(ArticlesApiHandler.class);

    private final ArticlesApi api;

    public ArticlesApiHandler(ArticlesApi api) {
        this.api = api;
    }

    @Deprecated
    public ArticlesApiHandler() {
        this(new ArticlesApiImpl());
    }

    public void mount(RouterBuilder builder) {
        builder.operation("CreateArticle").handler(this::createArticle);
        builder.operation("DeleteArticle").handler(this::deleteArticle);
        builder.operation("GetArticle").handler(this::getArticle);
        builder.operation("GetArticles").handler(this::getArticles);
        builder.operation("GetArticlesFeed").handler(this::getArticlesFeed);
        builder.operation("UpdateArticle").handler(this::updateArticle);
    }

    private void createArticle(RoutingContext routingContext) {
        logger.info("createArticle()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        RequestParameter body = requestParameters.body();
        CreateArticleRequest article = body != null ? DatabindCodec.mapper().convertValue(body.get(), new TypeReference<CreateArticleRequest>(){}) : null;

        logger.debug("Parameter article is {}", article);

        api.createArticle(article)
            .onSuccess(apiResponse -> {
                routingContext.response().setStatusCode(apiResponse.getStatusCode());
                if (apiResponse.hasData()) {
                    routingContext.json(apiResponse.getData());
                } else {
                    routingContext.response().end();
                }
            })
            .onFailure(routingContext::fail);
    }

    private void deleteArticle(RoutingContext routingContext) {
        logger.info("deleteArticle()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;

        logger.debug("Parameter slug is {}", slug);

        api.deleteArticle(slug)
            .onSuccess(apiResponse -> {
                routingContext.response().setStatusCode(apiResponse.getStatusCode());
                if (apiResponse.hasData()) {
                    routingContext.json(apiResponse.getData());
                } else {
                    routingContext.response().end();
                }
            })
            .onFailure(routingContext::fail);
    }

    private void getArticle(RoutingContext routingContext) {
        logger.info("getArticle()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;

        logger.debug("Parameter slug is {}", slug);

        api.getArticle(slug)
            .onSuccess(apiResponse -> {
                routingContext.response().setStatusCode(apiResponse.getStatusCode());
                if (apiResponse.hasData()) {
                    routingContext.json(apiResponse.getData());
                } else {
                    routingContext.response().end();
                }
            })
            .onFailure(routingContext::fail);
    }

    private void getArticles(RoutingContext routingContext) {
        logger.info("getArticles()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String tag = requestParameters.queryParameter("tag") != null ? requestParameters.queryParameter("tag").getString() : null;
        String author = requestParameters.queryParameter("author") != null ? requestParameters.queryParameter("author").getString() : null;
        String favorited = requestParameters.queryParameter("favorited") != null ? requestParameters.queryParameter("favorited").getString() : null;
        Integer offset = requestParameters.queryParameter("offset") != null ? requestParameters.queryParameter("offset").getInteger() : null;
        Integer limit = requestParameters.queryParameter("limit") != null ? requestParameters.queryParameter("limit").getInteger() : 20;

        logger.debug("Parameter tag is {}", tag);
        logger.debug("Parameter author is {}", author);
        logger.debug("Parameter favorited is {}", favorited);
        logger.debug("Parameter offset is {}", offset);
        logger.debug("Parameter limit is {}", limit);

        api.getArticles(tag, author, favorited, offset, limit)
            .onSuccess(apiResponse -> {
                routingContext.response().setStatusCode(apiResponse.getStatusCode());
                if (apiResponse.hasData()) {
                    routingContext.json(apiResponse.getData());
                } else {
                    routingContext.response().end();
                }
            })
            .onFailure(routingContext::fail);
    }

    private void getArticlesFeed(RoutingContext routingContext) {
        logger.info("getArticlesFeed()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        Integer offset = requestParameters.queryParameter("offset") != null ? requestParameters.queryParameter("offset").getInteger() : null;
        Integer limit = requestParameters.queryParameter("limit") != null ? requestParameters.queryParameter("limit").getInteger() : 20;

        logger.debug("Parameter offset is {}", offset);
        logger.debug("Parameter limit is {}", limit);

        api.getArticlesFeed(offset, limit)
            .onSuccess(apiResponse -> {
                routingContext.response().setStatusCode(apiResponse.getStatusCode());
                if (apiResponse.hasData()) {
                    routingContext.json(apiResponse.getData());
                } else {
                    routingContext.response().end();
                }
            })
            .onFailure(routingContext::fail);
    }

    private void updateArticle(RoutingContext routingContext) {
        logger.info("updateArticle()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;
        RequestParameter body = requestParameters.body();
        UpdateArticleRequest article = body != null ? DatabindCodec.mapper().convertValue(body.get(), new TypeReference<UpdateArticleRequest>(){}) : null;

        logger.debug("Parameter slug is {}", slug);
        logger.debug("Parameter article is {}", article);

        api.updateArticle(slug, article)
            .onSuccess(apiResponse -> {
                routingContext.response().setStatusCode(apiResponse.getStatusCode());
                if (apiResponse.hasData()) {
                    routingContext.json(apiResponse.getData());
                } else {
                    routingContext.response().end();
                }
            })
            .onFailure(routingContext::fail);
    }

}
