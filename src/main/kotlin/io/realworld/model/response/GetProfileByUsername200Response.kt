package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.Profile
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param profile
 */
data class GetProfileByUsername200Response(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("profile", required = true) val profile: Profile
) {

}

