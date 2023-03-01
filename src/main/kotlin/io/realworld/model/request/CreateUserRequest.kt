package io.realworld.model.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.NewUser
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param user
 */
data class CreateUserRequest(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("user", required = true) val user: NewUser
) {

}

