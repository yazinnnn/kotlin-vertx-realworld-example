package io.realworld.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetArticlesFeed200Response   {

  private List<Article> articles = new ArrayList<>();
  private Integer articlesCount;

  public GetArticlesFeed200Response () {

  }

  public GetArticlesFeed200Response (List<Article> articles, Integer articlesCount) {
    this.articles = articles;
    this.articlesCount = articlesCount;
  }


  @JsonProperty("articles")
  public List<Article> getArticles() {
    return articles;
  }
  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }


  @JsonProperty("articlesCount")
  public Integer getArticlesCount() {
    return articlesCount;
  }
  public void setArticlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetArticlesFeed200Response getArticlesFeed200Response = (GetArticlesFeed200Response) o;
    return Objects.equals(articles, getArticlesFeed200Response.articles) &&
        Objects.equals(articlesCount, getArticlesFeed200Response.articlesCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articles, articlesCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetArticlesFeed200Response {\n");

    sb.append("    articles: ").append(toIndentedString(articles)).append("\n");
    sb.append("    articlesCount: ").append(toIndentedString(articlesCount)).append("\n");
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
