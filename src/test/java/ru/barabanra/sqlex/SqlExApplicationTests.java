package ru.barabanra.sqlex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Container;
import ru.barabanra.sqlex.config.PostgresTestContainerRule;

@SpringBootTest
class SqlExApplicationTests {

    @Container
    private final static PostgresTestContainerRule testContainer = PostgresTestContainerRule.getInstance();

    @Test
    void contextLoads() {
    }

}
