package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile   {
  
  private String username;
  private String bio;
  private String image;
  private Boolean following;

  public Profile () {

  }

  public Profile (String username, String bio, String image, Boolean following) {
    this.username = username;
    this.bio = bio;
    this.image = image;
    this.following = following;
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

    
  @JsonProperty("following")
  public Boolean getFollowing() {
    return following;
  }
  public void setFollowing(Boolean following) {
    this.following = following;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(username, profile.username) &&
        Objects.equals(bio, profile.bio) &&
        Objects.equals(image, profile.image) &&
        Objects.equals(following, profile.following);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, bio, image, following);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    bio: ").append(toIndentedString(bio)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    following: ").append(toIndentedString(following)).append("\n");
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
