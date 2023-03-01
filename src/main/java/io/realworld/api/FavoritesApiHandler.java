package io.realworld.api;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FavoritesApiHandler {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesApiHandler.class);

    private final FavoritesApi api;

    public FavoritesApiHandler(FavoritesApi api) {
        this.api = api;
    }


    public void mount(RouterBuilder builder) {
        builder.operation("createArticleFavorite").handler(this::createArticleFavorite);
        builder.operation("deleteArticleFavorite").handler(this::deleteArticleFavorite);
    }

    private void createArticleFavorite(RoutingContext routingContext) {
        logger.info("createArticleFavorite()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;

        logger.debug("Parameter slug is {}", slug);

        api.createArticleFavorite(slug)
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

    private void deleteArticleFavorite(RoutingContext routingContext) {
        logger.info("deleteArticleFavorite()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String slug = requestParameters.pathParameter("slug") != null ? requestParameters.pathParameter("slug").getString() : null;

        logger.debug("Parameter slug is {}", slug);

        api.deleteArticleFavorite(slug)
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
