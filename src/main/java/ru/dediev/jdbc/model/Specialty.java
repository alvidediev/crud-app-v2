package ru.dediev.jdbc.model;

import lombok.Data;

@Data
public class Specialty {
    private Integer id;
    private String name;
    private int developerId;
    private Status status;
}
