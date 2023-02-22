package ru.dediev.jdbc.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Developer {
    private Integer id;
    private String firstName;
    private String lastName;
    List<Skill> skill = new ArrayList<>();
    private Specialty specialty;
    private Status status;
}
