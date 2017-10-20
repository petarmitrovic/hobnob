package com.neperix.hobnob.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.neperix.hobnob.config.posting.PostingApiInProcessConfig;
import com.neperix.hobnob.posting.ArticleRepository;
import com.neperix.hobnob.posting.ArticleService;

@ComponentScan
@Import(PostingApiInProcessConfig.class)
@Configuration
@SpringBootApplication
public class HobnobBoot {

  @Bean
  public ArticleService articleService(ArticleRepository articleRepository) {
    return new ArticleService(articleRepository);
  }

  public static void main(String[] args) {
    SpringApplication.run(HobnobBoot.class, args);
  }
}
