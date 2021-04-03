package com.minimarket.minimarketapp.domain.service.mappers;

public interface AbstractMapper<T, S> {

        T mapToEntity(S dto);

        S mapToDTO(T entity);

}
