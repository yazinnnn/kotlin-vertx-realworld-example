package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment   {

  private Integer id;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private String body;
  private Profile author;

  public Comment () {

  }

  public Comment (Integer id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String body, Profile author) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.body = body;
    this.author = author;
  }


  @JsonProperty("id")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }


  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  @JsonProperty("body")
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }


  @JsonProperty("author")
  public Profile getAuthor() {
    return author;
  }
  public void setAuthor(Profile author) {
    this.author = author;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id) &&
        Objects.equals(createdAt, comment.createdAt) &&
        Objects.equals(updatedAt, comment.updatedAt) &&
        Objects.equals(body, comment.body) &&
        Objects.equals(author, comment.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, body, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
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
