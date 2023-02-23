package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetProfileByUsername200Response   {

  private Profile profile;

  public GetProfileByUsername200Response () {

  }

  public GetProfileByUsername200Response (Profile profile) {
    this.profile = profile;
  }


  @JsonProperty("profile")
  public Profile getProfile() {
    return profile;
  }
  public void setProfile(Profile profile) {
    this.profile = profile;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetProfileByUsername200Response getProfileByUsername200Response = (GetProfileByUsername200Response) o;
    return Objects.equals(profile, getProfileByUsername200Response.profile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetProfileByUsername200Response {\n");

    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
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
