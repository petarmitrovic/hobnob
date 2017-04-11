package com.neperix.hobnob.posting;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  
  public void submitArticle(String title, String text, Long authorId) {
    this.articleRepository.save(new Article(title, text, authorId));
  }
}
