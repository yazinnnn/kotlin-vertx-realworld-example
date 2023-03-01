package io.realworld.entity

import javax.persistence.Entity

@Entity
class UserEntity(
  var username: String
) : BaseEntity()


//    @Schema(example = "null", required = true, description = "")
//    @get:JsonProperty("email", required = true) val email: kotlin.String,
//
//    @Schema(example = "null", required = true, description = "")
//    @get:JsonProperty("token", required = true) val token: kotlin.String,
//
//    @Schema(example = "null", required = true, description = "")
//    @get:JsonProperty("username", required = true) val username: kotlin.String,
//
//    @Schema(example = "null", required = true, description = "")
//    @get:JsonProperty("bio", required = true) val bio: kotlin.String,
//
//    @Schema(example = "null", required = true, description = "")
//    @get:JsonProperty("image", required = true) val image: kotlin.String
