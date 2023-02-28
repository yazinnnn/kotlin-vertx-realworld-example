package io.realworld.model.request


import com.fasterxml.jackson.annotation.JsonProperty

data class Login(
  @JsonProperty("user")
  val user: User
) {
  data class User(
    @JsonProperty("email")
    val email: String, // jake@jake.jake
    @JsonProperty("password")
    val password: String // jakejake
  )
}
