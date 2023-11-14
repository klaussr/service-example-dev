package ru.budgett.data;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class User {
    private Long id;
    @NotNull(message = "name can not be null")
    private String name;
    @NotNull(message = "birthDate can not be null")
    private LocalDate birthDate;
    @NotNull(message = "isAdmin can not be null")
    private Boolean isAdmin;
}