package ru.budgett.helpers;

import ru.budgett.data.User;

import java.time.LocalDate;

public class UserHelper {
    public static User createUser() {
        return new User()
                .setId(1L)
                .setIsAdmin(true)
                .setBirthDate(LocalDate.of(1989, 1, 1))
                .setName("Name")
                ;

    }
}
