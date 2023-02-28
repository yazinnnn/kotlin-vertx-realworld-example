package io.realworld.model.response


import com.fasterxml.jackson.annotation.JsonProperty

data class Profile(
  @JsonProperty("bio")
  val bio: String, // I work at statefarm
  @JsonProperty("following")
  val following: Boolean, // false
  @JsonProperty("image")
  val image: String, // https://api.realworld.io/images/smiley-cyrus.jpg
  @JsonProperty("username")
  val username: String // jake
) {
  data class Single(val profile: Profile)
}
