package com.neperix.hobnob.config.posting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neperix.hobnob.posting.ArticleServiceInProcess;
import com.neperix.hobnob.posting.api.ArticleService;

@Configuration
public class PostingApiInProcessConfig {

  @Bean
  public ArticleService articleServiceApi(com.neperix.hobnob.posting.ArticleService articleService) {
    return new ArticleServiceInProcess(articleService);
  }
}
