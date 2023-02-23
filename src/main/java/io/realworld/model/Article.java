package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article   {

  private String slug;
  private String title;
  private String description;
  private String body;
  private List<String> tagList = new ArrayList<>();
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private Boolean favorited;
  private Integer favoritesCount;
  private Profile author;

  public Article () {

  }

  public Article (String slug, String title, String description, String body, List<String> tagList, OffsetDateTime createdAt, OffsetDateTime updatedAt, Boolean favorited, Integer favoritesCount, Profile author) {
    this.slug = slug;
    this.title = title;
    this.description = description;
    this.body = body;
    this.tagList = tagList;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.favorited = favorited;
    this.favoritesCount = favoritesCount;
    this.author = author;
  }


  @JsonProperty("slug")
  public String getSlug() {
    return slug;
  }
  public void setSlug(String slug) {
    this.slug = slug;
  }


  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }


  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }


  @JsonProperty("body")
  public String getBody() {
    return body;
  }
  public void setBody(String body) {
    this.body = body;
  }


  @JsonProperty("tagList")
  public List<String> getTagList() {
    return tagList;
  }
  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
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


  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }


  @JsonProperty("favoritesCount")
  public Integer getFavoritesCount() {
    return favoritesCount;
  }
  public void setFavoritesCount(Integer favoritesCount) {
    this.favoritesCount = favoritesCount;
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
    Article article = (Article) o;
    return Objects.equals(slug, article.slug) &&
        Objects.equals(title, article.title) &&
        Objects.equals(description, article.description) &&
        Objects.equals(body, article.body) &&
        Objects.equals(tagList, article.tagList) &&
        Objects.equals(createdAt, article.createdAt) &&
        Objects.equals(updatedAt, article.updatedAt) &&
        Objects.equals(favorited, article.favorited) &&
        Objects.equals(favoritesCount, article.favoritesCount) &&
        Objects.equals(author, article.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(slug, title, description, body, tagList, createdAt, updatedAt, favorited, favoritesCount, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");

    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    tagList: ").append(toIndentedString(tagList)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    favorited: ").append(toIndentedString(favorited)).append("\n");
    sb.append("    favoritesCount: ").append(toIndentedString(favoritesCount)).append("\n");
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
