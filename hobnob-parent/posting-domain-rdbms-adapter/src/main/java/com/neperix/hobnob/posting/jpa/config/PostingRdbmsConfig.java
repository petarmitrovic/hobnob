package com.neperix.hobnob.posting.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.neperix.hobnob.posting.Article;
import com.neperix.hobnob.posting.jpa.JpaArticleRepository;

@EntityScan(basePackageClasses = Article.class)
@EnableJpaRepositories(basePackageClasses = JpaArticleRepository.class)
public class PostingRdbmsConfig {

}
