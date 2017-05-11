package com.neperix.hobnob.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
}
