package ru.dediev.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Integer id;
    private String name;
    private Integer developerId;
    private Status status;
}
