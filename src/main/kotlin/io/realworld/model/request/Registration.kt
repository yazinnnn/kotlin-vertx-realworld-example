package io.realworld.model.request


import com.fasterxml.jackson.annotation.JsonProperty

data class Registration(
    @JsonProperty("user")
    val user: User
) {
    data class User(
        @JsonProperty("email")
        val email: String, // jake@jake.jake
        @JsonProperty("password")
        val password: String, // jakejake
        @JsonProperty("username")
        val username: String // Jacob
    )
}
