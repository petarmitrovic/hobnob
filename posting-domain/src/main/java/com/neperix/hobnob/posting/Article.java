package com.neperix.hobnob.posting;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "articles")
@Getter
@AllArgsConstructor
public class Article extends com.neperix.hobnob.domain.Entity {

  private String title;
  private String text;
  private Long authorId;

  protected Article() {
    // Needed for ORM
  }
}
