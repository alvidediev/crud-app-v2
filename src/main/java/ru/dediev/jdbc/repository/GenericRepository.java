package ru.dediev.jdbc.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);

    Integer getId();

    List<T> getAll();

    T save(T t);

    T update(T t);

    T deleteById(ID id);
}
