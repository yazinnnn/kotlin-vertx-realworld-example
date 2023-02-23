package io.realworld.api;

import io.realworld.model.CreateUserRequest;
import io.realworld.model.LoginRequest;
import io.realworld.model.UpdateCurrentUserRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAndAuthenticationApiHandler {

  private static final Logger logger = LoggerFactory.getLogger(UserAndAuthenticationApiHandler.class);

  private final UserAndAuthenticationApi api;

  public UserAndAuthenticationApiHandler(UserAndAuthenticationApi api) {
    this.api = api;
  }

  @Deprecated
  public UserAndAuthenticationApiHandler() {
    this(new UserAndAuthenticationApiImpl());
  }

  public void mount(RouterBuilder builder) {
    builder.operation("CreateUser").handler(this::createUser);
    builder.operation("GetCurrentUser").handler(this::getCurrentUser);
    builder.operation("Login").handler(this::login);
    builder.operation("UpdateCurrentUser").handler(this::updateCurrentUser);
  }

  private void createUser(RoutingContext routingContext) {
    logger.info("createUser()");
    System.out.println(123);

    // Param extraction
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

    CreateUserRequest body = requestParameters.body() != null ? DatabindCodec.mapper().convertValue(requestParameters.body().get(), new TypeReference<>() {
    }) : null;

    logger.debug("Parameter body is {}", body);
    System.out.println(body);

    api.createUser(body).onSuccess(apiResponse -> {
      routingContext.response().setStatusCode(apiResponse.getStatusCode());
      if (apiResponse.hasData()) {
        routingContext.json(apiResponse.getData());
      } else {
        routingContext.response().end();
      }
    }).onFailure(routingContext::fail);
  }

  private void getCurrentUser(RoutingContext routingContext) {
    logger.info("getCurrentUser()");

    // Param extraction
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);


    api.getCurrentUser().onSuccess(apiResponse -> {
      routingContext.response().setStatusCode(apiResponse.getStatusCode());
      if (apiResponse.hasData()) {
        routingContext.json(apiResponse.getData());
      } else {
        routingContext.response().end();
      }
    }).onFailure(routingContext::fail);
  }

  private void login(RoutingContext routingContext) {
    logger.info("login()");

    // Param extraction
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

    LoginRequest body = requestParameters.body() != null ? DatabindCodec.mapper().convertValue(requestParameters.body().get(), new TypeReference<>() {
    }) : null;

    logger.debug("Parameter body is {}", body);

    api.login(body).onSuccess(apiResponse -> {
      routingContext.response().setStatusCode(apiResponse.getStatusCode());
      if (apiResponse.hasData()) {
        routingContext.json(apiResponse.getData());
      } else {
        routingContext.response().end();
      }
    }).onFailure(routingContext::fail);
  }

  private void updateCurrentUser(RoutingContext routingContext) {
    logger.info("updateCurrentUser()");

    // Param extraction
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

    UpdateCurrentUserRequest body = requestParameters.body() != null ? DatabindCodec.mapper().convertValue(requestParameters.body().get(), new TypeReference<UpdateCurrentUserRequest>() {
    }) : null;

    logger.debug("Parameter body is {}", body);

    api.updateCurrentUser(body).onSuccess(apiResponse -> {
      routingContext.response().setStatusCode(apiResponse.getStatusCode());
      if (apiResponse.hasData()) {
        routingContext.json(apiResponse.getData());
      } else {
        routingContext.response().end();
      }
    }).onFailure(routingContext::fail);
  }

}
