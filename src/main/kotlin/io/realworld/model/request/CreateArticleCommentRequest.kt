package io.realworld.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.NewComment
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param comment
 */
data class CreateArticleCommentRequest(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("comment", required = true) val comment: NewComment
) {

}

