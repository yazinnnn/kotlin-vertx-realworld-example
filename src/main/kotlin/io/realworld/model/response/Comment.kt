package io.realworld.model.response


import com.fasterxml.jackson.annotation.JsonProperty

data class Comment(
  @JsonProperty("author")
  val author: Author,
  @JsonProperty("body")
  val body: String, // It takes a Jacobian
  @JsonProperty("createdAt")
  val createdAt: String, // 2016-02-18T03:22:56.637Z
  @JsonProperty("id")
  val id: Int, // 1
  @JsonProperty("updatedAt")
  val updatedAt: String // 2016-02-18T03:22:56.637Z
) {
  data class Author(
    @JsonProperty("bio")
    val bio: String, // I work at statefarm
    @JsonProperty("following")
    val following: Boolean, // false
    @JsonProperty("image")
    val image: String, // https://i.stack.imgur.com/xHWG8.jpg
    @JsonProperty("username")
    val username: String // jake
  )

  data class Single(val comment: Comment)
  data class Multiple(val comments: List<Comment>)
}
