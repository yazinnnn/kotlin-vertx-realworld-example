package io.realworld.model.response


import com.fasterxml.jackson.annotation.JsonProperty

data class Article(
  @JsonProperty("author") val author: Author,
  @JsonProperty("body") val body: String, // It takes a Jacobian
  @JsonProperty("createdAt") val createdAt: String, // 2016-02-18T03:22:56.637Z
  @JsonProperty("description") val description: String, // Ever wonder how?
  @JsonProperty("favorited") val favorited: Boolean, // false
  @JsonProperty("favoritesCount") val favoritesCount: Int, // 0
  @JsonProperty("slug") val slug: String, // how-to-train-your-dragon
  @JsonProperty("tagList") val tagList: List<String>,
  @JsonProperty("title") val title: String, // How to train your dragon
  @JsonProperty("updatedAt") val updatedAt: String // 2016-02-18T03:48:35.824Z
) {
  data class Author(
    @JsonProperty("bio") val bio: String, // I work at statefarm
    @JsonProperty("following") val following: Boolean, // false
    @JsonProperty("image") val image: String, // https://i.stack.imgur.com/xHWG8.jpg
    @JsonProperty("username") val username: String // jake
  )

  data class Single(val article: Article)
  data class Multiple(val articles: List<Article>, val articlesCount: Int = articles.size)
}
