package com.neperix.hobnob.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neperix.hobnob.posting.api.Article;
import com.neperix.hobnob.posting.api.ArticleService;

import lombok.AllArgsConstructor;

@RestController("/articles")
@AllArgsConstructor
public class ArticleController {

	@Autowired
	private final ArticleService articleService;
	
	@GetMapping
	List<Article> list() {
		return this.articleService.list();
	}
}
