package ru.budgett.service.api;

import ru.budgett.data.User;

import java.util.List;

public interface UserService {
    /**
     * Сохранение пользователя.
     */
    void saveUser(User user);

    /**
     * Удаление пользователя.
     */
    void deleteUser(long idUser);

    /**
     * получение пользователя.
     */
    User getUser(long idUser);

    /**
     * Получение все пользователей
     */
    List<User> getAllUsers();


}
