package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.Article
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param article
 */
data class CreateArticle201Response(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("article", required = true) val article: Article
) {

}

