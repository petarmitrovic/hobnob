package com.neperix.hobnob.posting.api;

public interface ArticleService {

  void submit(String title, String text, Long authorId);
}
