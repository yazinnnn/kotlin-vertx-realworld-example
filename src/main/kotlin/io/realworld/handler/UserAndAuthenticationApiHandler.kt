package io.realworld.handler

import io.realworld.api.UserAndAuthenticationApi
import io.realworld.extensions.handleResponse
import io.realworld.extensions.mapTo
import io.realworld.model.request.CreateUserRequest
import io.realworld.model.request.LoginRequest
import io.realworld.model.request.UpdateCurrentUserRequest
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.ext.web.validation.RequestParameters
import io.vertx.ext.web.validation.ValidationHandler
import org.slf4j.LoggerFactory


class UserAndAuthenticationApiHandler(private val api: UserAndAuthenticationApi) {
  fun mount(builder: RouterBuilder) {
    builder.operation("CreateUser").handler(::createUser)
    builder.operation("GetCurrentUser").handler(::getCurrentUser)
    builder.operation("Login").handler(::login)
    builder.operation("UpdateCurrentUser").handler(::updateCurrentUser)
  }

  private fun createUser(routingContext: RoutingContext) {
    logger.info("createUser()")
    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val body = requestParameters.body().jsonObject.mapTo<CreateUserRequest>()
    logger.debug("Parameter body is {}", body)
    api.createUser(body).handleResponse(routingContext)
  }

  private fun getCurrentUser(routingContext: RoutingContext) {
    logger.info("getCurrentUser()")
    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    api.getCurrentUser(routingContext.user().principal().getLong("uid")).handleResponse(routingContext)
  }

  private fun login(routingContext: RoutingContext) {
    logger.info("login()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val body = requestParameters.body().jsonObject.mapTo<LoginRequest>()
    logger.debug("Parameter body is {}", body)
    api.login(body).handleResponse(routingContext)
  }

  private fun updateCurrentUser(routingContext: RoutingContext) {
    logger.info("updateCurrentUser()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val body = requestParameters.body().jsonObject.mapTo<UpdateCurrentUserRequest>()
    val uid = routingContext.user().principal().getLong("uid")
    logger.debug("Parameter body is {}", body)
    api.updateCurrentUser(uid, body).handleResponse(routingContext)
  }

  companion object {
    private val logger = LoggerFactory.getLogger(UserAndAuthenticationApiHandler::class.java)
  }
}
