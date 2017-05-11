package com.neperix.hobnob.posting;

import java.util.List;
import java.util.stream.Collectors;

import com.neperix.hobnob.posting.api.Article;
import com.neperix.hobnob.posting.api.ArticleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final com.neperix.hobnob.posting.ArticleService articleService;

  public void submit(String title, String text, Long authorId) {
    this.articleService.submitArticle(title, text, authorId);
  }

  @Override
  public List<Article> list() {
    return this.articleService.list().stream().map(a -> new Article(a.getTitle(), a.getText(), a.getAuthorId()))
        .collect(Collectors.toList());
  }

}
