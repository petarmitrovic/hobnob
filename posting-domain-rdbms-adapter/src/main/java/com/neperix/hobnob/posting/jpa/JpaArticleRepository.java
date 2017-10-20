package com.neperix.hobnob.posting.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neperix.hobnob.posting.Article;
import com.neperix.hobnob.posting.ArticleRepository;

@Repository
public interface JpaArticleRepository extends ArticleRepository, JpaRepository<Article, Long> {

}
