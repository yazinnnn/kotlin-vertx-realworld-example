package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUser   {
  
  private String email;
  private String password;
  private String username;
  private String bio;
  private String image;

  public UpdateUser () {

  }

  public UpdateUser (String email, String password, String username, String bio, String image) {
    this.email = email;
    this.password = password;
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

    
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
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
    UpdateUser updateUser = (UpdateUser) o;
    return Objects.equals(email, updateUser.email) &&
        Objects.equals(password, updateUser.password) &&
        Objects.equals(username, updateUser.username) &&
        Objects.equals(bio, updateUser.bio) &&
        Objects.equals(image, updateUser.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, username, bio, image);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateUser {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
