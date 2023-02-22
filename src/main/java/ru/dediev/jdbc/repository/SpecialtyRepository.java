package ru.dediev.jdbc.repository;


import ru.dediev.jdbc.model.Specialty;

public interface SpecialtyRepository extends GenericRepository<Specialty, Integer>{

    Specialty getDeveloperSpecialty(int id);

}
