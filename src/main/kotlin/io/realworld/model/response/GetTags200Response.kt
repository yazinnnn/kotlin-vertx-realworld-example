package io.realworld.model.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 *
 * @param tags
 */
data class GetTags200Response(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("tags", required = true) val tags: kotlin.collections.List<kotlin.String>
) {

}

