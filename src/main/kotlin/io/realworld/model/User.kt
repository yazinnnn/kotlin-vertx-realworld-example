package io.realworld.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param email
 * @param token
 * @param username
 * @param bio
 * @param image
 */
data class User(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("email", required = true) val email: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("token", required = true) val token: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("username", required = true) val username: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("bio", required = true) val bio: kotlin.String?,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("image", required = true) val image: kotlin.String?
) {

}

