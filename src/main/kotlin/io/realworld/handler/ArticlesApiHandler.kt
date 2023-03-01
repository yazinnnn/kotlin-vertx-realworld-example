package io.realworld.handler;

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.convertValue
import io.realworld.api.ArticlesApi
import io.realworld.extensions.handleResponse
import io.realworld.model.request.CreateArticleRequest
import io.realworld.model.request.UpdateArticleRequest
import io.vertx.core.json.jackson.DatabindCodec
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.openapi.RouterBuilder
import io.vertx.ext.web.validation.RequestParameters
import io.vertx.ext.web.validation.ValidationHandler
import org.slf4j.LoggerFactory

class ArticlesApiHandler(private val api: ArticlesApi) {
  fun mount(builder: RouterBuilder) {
    builder.operation("CreateArticle").handler(::createArticle)
    builder.operation("DeleteArticle").handler(::deleteArticle)
    builder.operation("GetArticle").handler(::getArticle)
    builder.operation("GetArticles").handler(::getArticles)
    builder.operation("GetArticlesFeed").handler(::getArticlesFeed)
    builder.operation("UpdateArticle").handler(::updateArticle)
  }


  private fun createArticle(routingContext: RoutingContext) {
    logger.info("createArticle()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val body = requestParameters.body()
    val article = DatabindCodec.mapper().convertValue<CreateArticleRequest>(body.get())

    logger.debug("Parameter article is {}", article)
    api.createArticle(article)
      .handleResponse(routingContext)
  }

  private fun deleteArticle(routingContext: RoutingContext) {
    logger.info("deleteArticle()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val slug = requestParameters.pathParameter("slug").string
    logger.debug("Parameter slug is {}", slug)
    api.deleteArticle(slug)
      .handleResponse(routingContext)
  }

  private fun getArticle(routingContext: RoutingContext) {
    logger.info("getArticle()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val slug = requestParameters.pathParameter("slug").string
    logger.debug("Parameter slug is {}", slug)
    api.getArticle(slug!!)
      .handleResponse(routingContext)
  }

  private fun getArticles(routingContext: RoutingContext) {
    logger.info("getArticles()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val tag = requestParameters.queryParameter("tag")?.string
    val author = requestParameters.queryParameter("author")?.string
    val favorited = requestParameters.queryParameter("favorited")?.string
    val offset = requestParameters.queryParameter("offset")?.integer ?: 0
    val limit = requestParameters.queryParameter("limit")?.integer ?: 20
    logger.debug("Parameter tag is {}", tag)
    logger.debug("Parameter author is {}", author)
    logger.debug("Parameter favorited is {}", favorited)
    logger.debug("Parameter offset is {}", offset)
    logger.debug("Parameter limit is {}", limit)
    api.getArticles(tag, author, favorited, offset, limit)
      .handleResponse(routingContext)
  }

  private fun getArticlesFeed(routingContext: RoutingContext) {
    logger.info("getArticlesFeed()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val offset = requestParameters.queryParameter("offset")?.integer ?: 0
    val limit = requestParameters.queryParameter("limit")?.integer ?: 20
    logger.debug("Parameter offset is {}", offset)
    logger.debug("Parameter limit is {}", limit)
    api.getArticlesFeed(offset, limit)
      .handleResponse(routingContext)
  }

  private fun updateArticle(routingContext: RoutingContext) {
    logger.info("updateArticle()")

    // Param extraction
    val requestParameters = routingContext.get<RequestParameters>(ValidationHandler.REQUEST_CONTEXT_KEY)
    val slug = requestParameters.pathParameter("slug").string
    val body = requestParameters.body()
    val article = DatabindCodec.mapper().convertValue<UpdateArticleRequest>(body.get())

    logger.debug("Parameter slug is {}", slug)
    logger.debug("Parameter article is {}", article)
    api.updateArticle(slug, article)
      .handleResponse(routingContext)
  }


  companion object {
    private val logger = LoggerFactory.getLogger(ArticlesApiHandler::class.java)
  }
}
