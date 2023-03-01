package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.Article
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param articles
 * @param articlesCount
 */
data class GetArticlesFeed200Response(

  @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("articles", required = true) val articles: kotlin.collections.List<Article>,

  @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("articlesCount", required = true) val articlesCount: kotlin.Int
) {

}

