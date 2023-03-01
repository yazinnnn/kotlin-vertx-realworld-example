package io.realworld.handler

import io.realworld.api.CommentsApi
import io.realworld.extensions.handleResponse
import io.realworld.extensions.mapTo
import io.realworld.model.request.CreateArticleCommentRequest
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.ext.web.validation.RequestParameters
import io.vertx.ext.web.validation.ValidationHandler
import org.slf4j.LoggerFactory


class CommentsApiHandler(private val api: CommentsApi) {
  fun mount(builder: RouterBuilder) {
    builder.operation("CreateArticleComment").handler(this::createArticleComment)
    builder.operation("DeleteArticleComment").handler(this::deleteArticleComment)
    builder.operation("GetArticleComments").handler(this::getArticleComments)
  }

  private fun createArticleComment(routingContext: RoutingContext) {
    logger.info("createArticleComment()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val slug = requestParameters.pathParameter("slug").string
    val comment = requestParameters.body().jsonObject.mapTo<CreateArticleCommentRequest>()
    logger.debug("Parameter slug is {}", slug)
    logger.debug("Parameter comment is {}", comment)
    api.createArticleComment(slug, comment)
      .handleResponse(routingContext)
  }

  private fun deleteArticleComment(routingContext: RoutingContext) {
    logger.info("deleteArticleComment()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val slug = requestParameters.pathParameter("slug").string
    val id = requestParameters.pathParameter("id").integer
    logger.debug("Parameter slug is {}", slug)
    logger.debug("Parameter id is {}", id)
    api.deleteArticleComment(slug, id)
      .handleResponse(routingContext)
  }

  private fun getArticleComments(routingContext: RoutingContext) {
    logger.info("getArticleComments()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val slug = requestParameters.pathParameter("slug").string
    api.getArticleComments(slug)
      .handleResponse(routingContext)
  }

  companion object {
    private val logger = LoggerFactory.getLogger(CommentsApiHandler::class.java)
  }
}

