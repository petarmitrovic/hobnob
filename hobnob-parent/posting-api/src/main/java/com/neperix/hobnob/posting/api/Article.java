package com.neperix.hobnob.posting.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Article {

  private String title;
  private String text;
  private Long authorId;
}
