package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.realworld.model.GenericErrorModelErrors
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid

/**
 *
 * @param errors
 */
data class GenericErrorModel(

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("errors", required = true) val errors: GenericErrorModelErrors
) {

}

