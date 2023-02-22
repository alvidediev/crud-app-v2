package ru.dediev.jdbc.service;

import ru.dediev.jdbc.model.Developer;
import ru.dediev.jdbc.model.Skill;

import java.util.List;

public interface SkillService {
    Skill save(Skill skill);
    int getId();
    Skill getById(Integer id);
    List<Skill> getAll();
    Skill update(Skill skill);
    void deleteById(Integer id);
}
