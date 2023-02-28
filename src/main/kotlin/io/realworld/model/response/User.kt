package io.realworld.model.response


import com.fasterxml.jackson.annotation.JsonProperty

data class User(
  @JsonProperty("bio")
  val bio: String, // I work at statefarm
  @JsonProperty("email")
  val email: String, // jake@jake.jake
  @JsonProperty("image")
  val image: Any, // null
  @JsonProperty("token")
  val token: String, // jwt.token.here
  @JsonProperty("username")
  val username: String // jake
) {
  data class Single(val user: User)
}
