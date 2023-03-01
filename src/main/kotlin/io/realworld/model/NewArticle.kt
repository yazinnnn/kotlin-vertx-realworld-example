package io.realworld.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.*

/**
 *
 * @param title
 * @param description
 * @param body
 * @param tagList
 */
data class NewArticle(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("title", required = true) val title: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("description", required = true) val description: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("body", required = true) val body: kotlin.String,

    @Schema(example = "null", description = "")
    @get:JsonProperty("tagList") val tagList: kotlin.collections.List<kotlin.String>? = null
) {

}

