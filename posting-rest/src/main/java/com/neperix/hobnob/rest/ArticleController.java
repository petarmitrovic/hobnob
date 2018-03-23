package com.neperix.hobnob.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neperix.hobnob.posting.api.Article;
import com.neperix.hobnob.posting.api.ArticleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

  @Autowired
  private final ArticleService articleService;

  @GetMapping
  List<Article> fetchAllArticles() {
    return this.articleService.list();
  }

  @PostMapping
  ResponseEntity<Boolean> submitAnArticle(ArticleCommand command) {
    this.articleService.submit(command.getTitle(), command.getText(), 1L);
    return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
  }
}
