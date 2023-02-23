package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateArticleComment200Response   {

  private Comment comment;

  public CreateArticleComment200Response () {

  }

  public CreateArticleComment200Response (Comment comment) {
    this.comment = comment;
  }


  @JsonProperty("comment")
  public Comment getComment() {
    return comment;
  }
  public void setComment(Comment comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArticleComment200Response createArticleComment200Response = (CreateArticleComment200Response) o;
    return Objects.equals(comment, createArticleComment200Response.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateArticleComment200Response {\n");

    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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
