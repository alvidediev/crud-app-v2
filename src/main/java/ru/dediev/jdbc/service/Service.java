package ru.dediev.jdbc.service;

import ru.dediev.jdbc.model.Skill;

import java.util.List;

public interface Service<T, ID> {
    T save(T id);

    int getId();

    T getById(ID id);

    List<T> getAll();

    T update(T skill);

    T deleteById(ID id);
}
