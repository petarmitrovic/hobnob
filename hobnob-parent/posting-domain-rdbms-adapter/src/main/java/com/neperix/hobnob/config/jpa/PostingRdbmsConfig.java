package com.neperix.hobnob.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.neperix.hobnob.posting.Article;
import com.neperix.hobnob.posting.jpa.JpaArticleRepository;

@Configuration
@EntityScan(basePackageClasses = Article.class)
@EnableJpaRepositories(basePackageClasses = JpaArticleRepository.class)
public class PostingRdbmsConfig {

}
