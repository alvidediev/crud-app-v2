package ru.dediev.jdbc.service.impl;

import ru.dediev.jdbc.model.Developer;
import ru.dediev.jdbc.repository.DevelopersRepository;
import ru.dediev.jdbc.repository.impl.DevelopersRepositoryImpl;
import ru.dediev.jdbc.service.DeveloperService;

import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {

    private final DevelopersRepository developersRepository = new DevelopersRepositoryImpl();

    @Override
    public Developer save(Developer developer) {
        return developersRepository.save(developer);
    }

    @Override
    public Developer getById(Integer id) {
        return developersRepository.getById(id);

    }

    @Override
    public List<Developer> getAll() {
        return developersRepository.getAll();
    }

    @Override
    public Developer update(Developer developer) {
        return developersRepository.update(developer);
    }

    @Override
    public void deleteById(Integer id) {
        developersRepository.deleteById(id);
    }

    @Override
    public int getId(){
        return developersRepository.getId();
    }
}
