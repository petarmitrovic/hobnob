package com.neperix.hobnob.posting;

import com.neperix.hobnob.posting.api.ArticleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

  private final com.neperix.hobnob.posting.ArticleService articleService;
  
  public void submit(String title, String text, Long authorId) {
    this.articleService.submitArticle(title, text, authorId);
  }

}
