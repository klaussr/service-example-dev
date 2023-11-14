package ru.budgett;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.OPENTABLE;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockContext.class, Application.class})
@Transactional
@AutoConfigureEmbeddedDatabase(provider = OPENTABLE)
public abstract class AbstractTest {
}
