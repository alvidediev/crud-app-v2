package ru.dediev.jdbc.service;

import ru.dediev.jdbc.model.Developer;

import java.util.List;

public interface DeveloperService {
        Developer save(Developer developer);
        Developer getById(Integer id);
        int getId();
        List<Developer> getAll();
        Developer update(Developer developer);
        void deleteById(Integer id);
}
