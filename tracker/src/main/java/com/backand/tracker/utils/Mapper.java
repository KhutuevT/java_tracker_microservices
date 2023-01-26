package com.backand.tracker.utils;

public interface Mapper<E extends AbstractBaseEntity, D extends AbstractDto> {
    E toEntity(D dto);

    D toDto(E entity);
}
