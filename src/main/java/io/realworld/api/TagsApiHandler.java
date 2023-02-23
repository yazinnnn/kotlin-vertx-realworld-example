package io.realworld.api;

import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TagsApiHandler {

    private static final Logger logger = LoggerFactory.getLogger(TagsApiHandler.class);

    private final TagsApi api;

    public TagsApiHandler(TagsApi api) {
        this.api = api;
    }

    @Deprecated
    public TagsApiHandler() {
        this(new TagsApiImpl());
    }

    public void mount(RouterBuilder builder) {
        builder.operation("GetTags").handler(this::getTags);
    }

    private void getTags(RoutingContext routingContext) {
        logger.info("getTags()");

        // Param extraction
        RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);



        api.getTags()
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
