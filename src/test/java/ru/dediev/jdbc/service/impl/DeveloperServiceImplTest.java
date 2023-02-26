package ru.dediev.jdbc.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dediev.jdbc.model.Developer;
import ru.dediev.jdbc.model.Skill;
import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.DevelopersRepository;
import ru.dediev.jdbc.service.DeveloperService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.dediev.jdbc.model.Status.ACTIVE;

@ExtendWith(MockitoExtension.class)
public class DeveloperServiceImplTest {

    @Mock
    private DevelopersRepository developersRepository;

    private Developer developer;

    private final List<Developer> developerList = new ArrayList<>();

    private final DeveloperService developerService = new DeveloperServiceImpl();

    @BeforeEach
    public void setUp() {
        developer = new Developer();
        List<Skill> skillList = Arrays.asList(
                new Skill(
                        1,
                        "Spring-Framework",
                        1,
                        null),
                new Skill(
                        2,
                        "JDBC",
                        1,
                        null)
        );
        Specialty specialty = new Specialty(
                1,
                "Java-developer",
                1,
                null);

        developer.setId(1);
        developer.setFirstName("Dediev");
        developer.setLastName("Alvi");
        developer.setSkill(skillList);
        developer.setSpecialty(specialty);
        developer.setStatus(null);

        developerList.add(developer);
        developerList.add(developer);
    }

    @Test
    public void saveDeveloperTest(){
        Specialty specialty = new Specialty(
                0,
                null,
                0,
                ACTIVE
        );
        Developer developerBeforeAddingToDatabase = new Developer(
                null,
                "Mansur",
                "Macuhaev",
                new ArrayList<>(),
                specialty,
                ACTIVE
        );

        Developer developerAfterAddingToDatabase = new Developer(
                13,
                "Mansur",
                "Macuhaev",
                new ArrayList<>(),
                specialty,
                ACTIVE
        );
        when(developersRepository.save(developerBeforeAddingToDatabase)).thenReturn(developerAfterAddingToDatabase);
        assertEquals(developerService.save(developerBeforeAddingToDatabase),
                     developersRepository.save(developerBeforeAddingToDatabase));
    }

    @Test
    public void getByIdTest() {
        when(developersRepository.getById(1)).thenReturn(developer);
        assertEquals(developerService.getById(1), developersRepository.getById(1));
    }

    @Test
    public void getAllTest(){
        when(developersRepository.getAll()).thenReturn(developerList);
        assertEquals(developerService.getAll(), developersRepository.getAll());
    }

    @Test
    public void updateTest(){
        when(developersRepository.update(developer)).thenReturn(developer);
        assertEquals(developerService.update(developer), developersRepository.update(developer));
    }

    @Test
    public void deleteTest(){
        developer = new Developer(
                null,
                null,
                null,
                new ArrayList<>(),
                null,
                Status.DELETED);

        when(developersRepository.deleteById(1)).thenReturn(developer);
        assertEquals(developerService.deleteById(1), developersRepository.deleteById(1));
    }
}
