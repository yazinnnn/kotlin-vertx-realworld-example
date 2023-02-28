package io.realworld.model.request


import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateUser(
    @JsonProperty("user")
    val user: User
) {
    data class User(
        @JsonProperty("bio")
        val bio: String, // I like to skateboard
        @JsonProperty("email")
        val email: String, // jake@jake.jake
        @JsonProperty("image")
        val image: String // https://i.stack.imgur.com/xHWG8.jpg
    )
}
