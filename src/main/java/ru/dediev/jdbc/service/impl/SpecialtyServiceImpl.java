package ru.dediev.jdbc.service.impl;

import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.repository.SpecialtyRepository;
import ru.dediev.jdbc.repository.impl.SpecialtyRepositoryImpl;
import ru.dediev.jdbc.service.SpecialtyService;

import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository = new SpecialtyRepositoryImpl();

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public int getId() {
        return specialtyRepository.getId();
    }

    @Override
    public Specialty getById(Integer id) {
        return specialtyRepository.getById(id);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    @Override
    public void deleteById(Integer id) {
        specialtyRepository.deleteById(id);
    }
}
