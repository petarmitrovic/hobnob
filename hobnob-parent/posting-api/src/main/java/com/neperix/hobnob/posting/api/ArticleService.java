package com.neperix.hobnob.posting.api;

import java.util.List;

public interface ArticleService {

  void submit(String title, String text, Long authorId);
  
  List<Article> list();
}
