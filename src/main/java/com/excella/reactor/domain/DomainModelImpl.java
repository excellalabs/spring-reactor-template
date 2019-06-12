package com.excella.reactor.domain;

public abstract class DomainModelImpl<T> implements DomainModel<T> {
    @Override
    public abstract T withId(Long id);
}
