package ru.dediev.jdbc.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dediev.jdbc.model.Skill;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.SkillRepository;
import ru.dediev.jdbc.service.SkillService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.dediev.jdbc.model.Status.ACTIVE;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    private Skill skill;

    private List<Skill> skillList = new ArrayList<>();

    private SkillService skillService = new SkillServiceImpl();

    @BeforeEach
    public void setUp(){
        skill = new Skill(
                1,
                "Spring-Framework",
                1,
                ACTIVE
        );

        skillList.add(skill);
        skillList.add(new Skill(
                2,
                "JDBC",
                1,
                ACTIVE
        ));
    }

    @Test
    public void saveTest(){
        skill = new Skill(
                5,
                "name",
                1,
                ACTIVE
        );
        when(skillRepository.save(skill)).thenReturn(skill);
        assertEquals(skillService.save(skill), skillRepository.save(skill));
    }

    @Test
    public void getSkillByIdTest(){
        when(skillRepository.getById(1)).thenReturn(skill);
        assertEquals(skillService.getById(1), skillRepository.getById(1));
    }

    @Test
    public void getAllTest(){
        when(skillRepository.getAll()).thenReturn(skillList);
        assertEquals(skillService.getAll(), skillRepository.getAll());
    }

    @Test
    public void updateTest(){
        when(skillRepository.update(skill)).thenReturn(skill);
        assertEquals(skillService.update(skill), skillRepository.update(skill));
    }

    @Test
    public void deleteSkillTest(){
        skill = new Skill();
        skill.setStatus(Status.DELETED);
        when(skillRepository.deleteById(1)).thenReturn(skill);
        assertEquals(skillService.deleteById(1), skillRepository.deleteById(1));
    }
}
