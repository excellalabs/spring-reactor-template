package com.excella.reactor.domain;

import java.io.Serializable;

public interface DomainModel<T> extends Serializable {
  T withId(Long id);
}
