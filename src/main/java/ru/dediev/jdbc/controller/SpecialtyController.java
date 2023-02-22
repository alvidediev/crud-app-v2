package ru.dediev.jdbc.controller;

import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.service.SpecialtyService;
import ru.dediev.jdbc.service.impl.SpecialtyServiceImpl;

import java.util.List;

public class SpecialtyController {

    private final SpecialtyService specialtyService = new SpecialtyServiceImpl();

    public Specialty create(Specialty specialty){
        return specialtyService.save(specialty);
    }

    public Specialty read(Integer id){
        return specialtyService.getById(id);
    }

    public int getId(){
        return specialtyService.getId();
    }

    public Specialty getById(int id){
        return specialtyService.getById(id);
    }

    public List<Specialty> readAll(){
        return specialtyService.getAll();
    }

    public void update(Integer id, String name){
        Specialty specialty = new Specialty();
        specialty.setId(id);
        specialty.setName(name);
        specialtyService.update(specialty);
    }

    public void delete(Integer id){
        specialtyService.deleteById(id);
    }
}
