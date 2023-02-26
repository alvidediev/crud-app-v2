package ru.dediev.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer {
    private Integer id;
    private String firstName;
    private String lastName;
    List<Skill> skill = new ArrayList<>();
    private Specialty specialty;
    private Status status;
}
