package ru.dediev.jdbc.repository;


import ru.dediev.jdbc.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Integer>{

    List<Skill> getAllDeveloperSkills(int developerId);

}
