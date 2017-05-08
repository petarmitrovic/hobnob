package com.neperix.hobnob.posting;

import java.util.List;

public interface ArticleRepository {

  void save(Article article);

	List<Article> findAll();
}
