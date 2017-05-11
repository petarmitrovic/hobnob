package com.neperix.hobnob.posting;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;

  public void submitArticle(String title, String text, Long authorId) {
    this.articleRepository.save(new Article(title, text, authorId));
  }

  public List<Article> list() {
    return this.articleRepository.findAll();
  }
}
