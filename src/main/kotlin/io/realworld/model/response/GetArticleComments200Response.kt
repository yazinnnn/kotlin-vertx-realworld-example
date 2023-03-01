package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.Comment
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param comments
 */
data class GetArticleComments200Response(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("comments", required = true) val comments: kotlin.collections.List<Comment>
) {

}

