package ru.dediev.jdbc.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.SpecialtyRepository;
import ru.dediev.jdbc.service.SpecialtyService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.dediev.jdbc.model.Status.ACTIVE;
import static ru.dediev.jdbc.model.Status.DELETED;

@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceImplTest {

    @Mock
    public SpecialtyRepository specialtyRepository;

    private Specialty specialty;

    private List<Specialty> specialties;

    private final SpecialtyService specialtyService = new SpecialtyServiceImpl();

    @BeforeEach
    public void setUp(){
        specialty = new Specialty(
                1,
                "C++ - developer",
                1,
                ACTIVE
        );
    }

    @Test
    public void saveSpecialtyTest(){
        Specialty specialtyAfterReturnFromBase = new Specialty(
                1,
                "C++ - developer",
                1,
                ACTIVE
        );
        when(specialtyRepository.save(specialty)).thenReturn(specialtyAfterReturnFromBase);
        assertEquals(specialtyService.save(specialty), specialtyRepository.save(specialty));
    }

    @Test
    public void getById(){
        when(specialtyRepository.getById(1)).thenReturn(specialty);
        assertEquals(specialtyService.getById(1), specialtyRepository.getById(1));
    }

    @Test
    public void getAll(){
        specialties = new ArrayList<>();

        specialties.add(specialty);

        when(specialtyRepository.getAll()).thenReturn(specialties);
        assertEquals(specialtyService.getAll(), specialtyRepository.getAll());
    }

    @Test
    public void deleteSkillTest(){
        specialty = new Specialty(
                null,
                null,
                0,
                DELETED
        );
        when(specialtyRepository.deleteById(1)).thenReturn(specialty);
        assertEquals(specialtyService.deleteById(1), specialtyRepository.deleteById(1));
    }

}
