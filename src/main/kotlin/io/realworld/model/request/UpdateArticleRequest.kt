package io.realworld.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.UpdateArticle
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param article
 */
data class UpdateArticleRequest(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("article", required = true) val article: UpdateArticle
) {

}

