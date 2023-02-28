package io.realworld.model.request


import com.fasterxml.jackson.annotation.JsonProperty

data class AddCommentstoanArticle(
    @JsonProperty("comment")
    val comment: Comment
) {
    data class Comment(
        @JsonProperty("body")
        val body: String // His name was my name too.
    )
}
