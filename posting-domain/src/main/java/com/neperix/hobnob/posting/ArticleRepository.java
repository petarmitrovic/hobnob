package com.neperix.hobnob.posting;

import java.util.List;

public interface ArticleRepository {

  Article save(Article article);

  List<Article> findAll();
}
