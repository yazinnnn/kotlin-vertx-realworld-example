package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.Comment
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param comment
 */
data class CreateArticleComment200Response(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("comment", required = true) val comment: Comment
) {

}

