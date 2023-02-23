package io.realworld.api;

import io.realworld.model.CreateArticleCommentRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentsApiHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommentsApiHandler.class);

    private final CommentsApi api;

    public CommentsApiHandler(CommentsApi api) {
        this.api = api;
    }

    @Deprecated
    public CommentsApiHandler() {
        this(new CommentsApiImpl());
    }

    public void mount(RouterBuilder builder) {
        builder.operation("createArticleComment").handler(this::createArticleComment);
        builder.operation("deleteArticleComment").handler(this::deleteArticleComment);
        builder.operation("getArticleComments").handler(this::getArticleComments);
    }

    private void createArticleComment(RoutingContext routingContext) {
        logger.info("createArticleComment()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;
        RequestParameter body = requestParameters.body();
        CreateArticleCommentRequest comment = body != null ? DatabindCodec.mapper().convertValue(body.get(), new TypeReference<CreateArticleCommentRequest>(){}) : null;

        logger.debug("Parameter slug is {}", slug);
        logger.debug("Parameter comment is {}", comment);

        api.createArticleComment(slug, comment)
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

    private void deleteArticleComment(RoutingContext routingContext) {
        logger.info("deleteArticleComment()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;
        Integer id = requestParameters.pathParameter("id") != null ? requestParameters.pathParameter("id").getInteger() : null;

        logger.debug("Parameter slug is {}", slug);
        logger.debug("Parameter id is {}", id);

        api.deleteArticleComment(slug, id)
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

    private void getArticleComments(RoutingContext routingContext) {
        logger.info("getArticleComments()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;

        logger.debug("Parameter slug is {}", slug);

        api.getArticleComments(slug)
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
