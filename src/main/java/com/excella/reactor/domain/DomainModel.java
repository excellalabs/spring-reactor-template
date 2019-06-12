package com.excella.reactor.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class DomainModel  {
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Id protected Long id;
}
