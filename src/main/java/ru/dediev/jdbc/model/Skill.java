package ru.dediev.jdbc.model;

import lombok.Data;

@Data
public class Skill {
    private Integer id;
    private String name;
    private int developerId;
    private Status status;


}
