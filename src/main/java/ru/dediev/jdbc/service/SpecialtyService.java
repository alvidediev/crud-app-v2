package ru.dediev.jdbc.service;

import ru.dediev.jdbc.model.Specialty;

import java.util.List;

public interface SpecialtyService {
    Specialty save(Specialty specialty);
    int getId();
    Specialty getById(Integer id);
    List<Specialty> getAll();
    Specialty update(Specialty specialty);
    void deleteById(Integer id);
}
