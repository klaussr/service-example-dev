package ru.budgett.repository.api;

import ru.budgett.data.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    /**
     * Создание нового пользователя
     */
    void createUser(User user);

    /**
     * Поиск пользователя по Id
     */
    Optional<User> findUser(long idUser);

    /**
     * Удаление пользователя
     */
    void deleteUser(long idUser);

    /**
     * Обновление пользователя
     */
    void updateUser(User user);

    /**
     * Нахождение всех пользователей
     */
    List<User> findAllUsers();
}
