package com.neperix.hobnob.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Entity {

	@Id
  protected Long id;
}
