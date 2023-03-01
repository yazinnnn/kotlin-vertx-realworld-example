package io.realworld.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param slug
 * @param title
 * @param description
 * @param body
 * @param tagList
 * @param createdAt
 * @param updatedAt
 * @param favorited
 * @param favoritesCount
 * @param author
 */
data class Article(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("slug", required = true) val slug: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("title", required = true) val title: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("description", required = true) val description: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("body", required = true) val body: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("tagList", required = true) val tagList: kotlin.collections.List<kotlin.String>,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("createdAt", required = true) val createdAt: java.time.OffsetDateTime,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("updatedAt", required = true) val updatedAt: java.time.OffsetDateTime,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("favorited", required = true) val favorited: kotlin.Boolean,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("favoritesCount", required = true) val favoritesCount: kotlin.Int,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("author", required = true) val author: Profile
) {

}

