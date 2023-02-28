package io.realworld.model.request


import com.fasterxml.jackson.annotation.JsonProperty

data class CreateArticle(
    @JsonProperty("article")
    val article: Article
) {
    data class Article(
        @JsonProperty("body")
        val body: String, // You have to believe
        @JsonProperty("description")
        val description: String, // Ever wonder how?
        @JsonProperty("tagList")
        val tagList: List<String>,
        @JsonProperty("title")
        val title: String // How to train your dragon
    )
}
