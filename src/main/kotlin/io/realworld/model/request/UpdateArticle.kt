package io.realworld.model.request


import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateArticle(
    @JsonProperty("article")
    val article: Article
) {
    data class Article(
        @JsonProperty("title")
        val title: String // Did you train your dragon?
    )
}
