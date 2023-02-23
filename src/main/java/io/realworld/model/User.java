package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User   {
  
  private String email;
  private String token;
  private String username;
  private String bio;
  private String image;

  public User () {

  }

  public User (String email, String token, String username, String bio, String image) {
    this.email = email;
    this.token = token;
    this.username = username;
    this.bio = bio;
    this.image = image;
  }

    
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

    
  @JsonProperty("token")
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }

    
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

    
  @JsonProperty("bio")
  public String getBio() {
    return bio;
  }
  public void setBio(String bio) {
    this.bio = bio;
  }

    
  @JsonProperty("image")
  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(email, user.email) &&
        Objects.equals(token, user.token) &&
        Objects.equals(username, user.username) &&
        Objects.equals(bio, user.bio) &&
        Objects.equals(image, user.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, token, username, bio, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    bio: ").append(toIndentedString(bio)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
