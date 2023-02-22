package ru.dediev.jdbc.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(ID id);

    int getId();

    List<T> getAll();

    T save(T t);

    T update(T t);

    void deleteById(ID id);
}
