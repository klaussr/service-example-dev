package ru.budgett.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.budgett.exceptions.UserNotFoundException;
import ru.budgett.repository.api.UserRepository;
import ru.budgett.data.User;
import ru.budgett.service.api.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional
    public void saveUser(User user) {
        Long userId = user.getId();

        if (userId == null) {
            repository.createUser(user);
            return;
        }

        repository.findUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        repository.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        repository.deleteUser(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long userId) {
        return repository.findUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAllUsers();
    }
}
