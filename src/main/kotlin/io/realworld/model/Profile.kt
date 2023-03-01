package io.realworld.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param username
 * @param bio
 * @param image
 * @param following
 */
data class Profile(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("username", required = true) val username: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("bio", required = true) val bio: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("image", required = true) val image: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("following", required = true) val following: kotlin.Boolean
) {

}

