package ru.dediev.jdbc.controller;

import ru.dediev.jdbc.model.Developer;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.service.DeveloperService;
import ru.dediev.jdbc.service.impl.DeveloperServiceImpl;

import java.util.List;

public class DevelopersController {

    private final DeveloperService developerService = new DeveloperServiceImpl();

    public Developer create(String firstName, String lastName) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setStatus(Status.ACTIVE);
        return developerService.save(developer);
    }

    public int getId() {
        return developerService.getId();
    }

    public Developer getById(int id) {
        return developerService.getById(id);
    }

    public Developer read(Integer id) {
        return developerService.getById(id);
    }

    public List<Developer> readAll() {
        return developerService.getAll();
    }

    public Developer update(Integer id, String firstName, String lastName) {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        return developerService.update(developer);
    }

    public Developer delete(Integer id) {
        return developerService.deleteById(id);
    }
}
