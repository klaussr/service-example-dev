package ru.budgett.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.budgett.AbstractTest;
import ru.budgett.data.User;
import ru.budgett.repository.api.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.budgett.helpers.UserHelper.createUser;


public class DaoTest extends AbstractTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testCreate() {
        User user = createUser().setId(null);

        repository.createUser(user);

        int size = repository.findAllUsers().size();

        assertThat(size).isEqualTo(3);
    }

    @Test
    public void testUpdate() {
        User user = createUser();

        repository.updateUser(user);

        User newUser = repository.findUser(user.getId()).get();

        assertThat(newUser).isEqualTo(user);
    }
}
