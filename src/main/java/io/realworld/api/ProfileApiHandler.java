package io.realworld.api;

import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfileApiHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProfileApiHandler.class);

    private final ProfileApi api;

    public ProfileApiHandler(ProfileApi api) {
        this.api = api;
    }

    @Deprecated
    public ProfileApiHandler() {
        this(new ProfileApiImpl());
    }

    public void mount(RouterBuilder builder) {
        builder.operation("followUserByUsername").handler(this::followUserByUsername);
        builder.operation("getProfileByUsername").handler(this::getProfileByUsername);
        builder.operation("unfollowUserByUsername").handler(this::unfollowUserByUsername);
    }

    private void followUserByUsername(RoutingContext routingContext) {
        logger.info("followUserByUsername()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String username = requestParameters.pathParameter("username") != null ? requestParameters.pathParameter("username").getString() : null;

        logger.debug("Parameter username is {}", username);

        api.followUserByUsername(username)
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

    private void getProfileByUsername(RoutingContext routingContext) {
        logger.info("getProfileByUsername()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String username = requestParameters.pathParameter("username") != null ? requestParameters.pathParameter("username").getString() : null;

        logger.debug("Parameter username is {}", username);

        api.getProfileByUsername(username)
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

    private void unfollowUserByUsername(RoutingContext routingContext) {
        logger.info("unfollowUserByUsername()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

        String username = requestParameters.pathParameter("username") != null ? requestParameters.pathParameter("username").getString() : null;

        logger.debug("Parameter username is {}", username);

        api.unfollowUserByUsername(username)
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
