package ru.dediev.jdbc.controller;

import ru.dediev.jdbc.model.Skill;
import ru.dediev.jdbc.service.impl.SkillServiceImpl;

import java.util.List;

public class SkillsController {

    private final SkillServiceImpl skillService = new SkillServiceImpl();

    public Skill create(Skill skill){
        return skillService.save(skill);
    }

    public Skill read(Integer id){
        return skillService.getById(id);
    }

    public int getId(){
        return skillService.getId();
    }

    public Skill getById(int id){
        return skillService.getById(id);
    }

    public List<Skill> readAll(){
        return skillService.getAll();
    }

    public void update(Integer id, String name){
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skillService.update(skill);
    }

    public Skill delete(Integer id){
        return skillService.deleteById(id);
    }
}
