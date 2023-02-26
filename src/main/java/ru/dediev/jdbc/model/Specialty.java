package ru.dediev.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {
    private Integer id;
    private String name;
    private Integer developerId;
    private Status status;
}
