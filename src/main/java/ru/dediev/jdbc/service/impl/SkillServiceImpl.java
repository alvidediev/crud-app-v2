package ru.dediev.jdbc.service.impl;

import ru.dediev.jdbc.model.Skill;
import ru.dediev.jdbc.repository.SkillRepository;
import ru.dediev.jdbc.repository.impl.SkillRepositoryImpl;
import ru.dediev.jdbc.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepositoryImpl skillRepository = new SkillRepositoryImpl();

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public int getId(){
        return skillRepository.getId();
    }

    @Override
    public Skill getById(Integer id) {
        return skillRepository.getById(id);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    @Override
    public Skill update(Skill skill) {
        return skillRepository.update(skill);
    }

    @Override
    public void deleteById(Integer id) {
        skillRepository.deleteById(id);
    }

//    public void selectSkill(int id, int i){
//        skillRepository.selectSkill(id, i);
//    }
}
