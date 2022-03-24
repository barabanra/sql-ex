package ru.barabanra.sqlex.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainerRule extends PostgreSQLContainer {

    private static PostgresTestContainerRule container;

    private PostgresTestContainerRule() {
        super("postgres:13.6-alpine");
        super.withDatabaseName("postgres");
    }

    public static PostgresTestContainerRule getInstance() {
        if (container == null) {
            container = new PostgresTestContainerRule();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("POSTGRES_TEST_CONTAINER_URL", container.getJdbcUrl());
        System.setProperty("POSTGRES_TEST_CONTAINER_USERNAME", container.getUsername());
        System.setProperty("POSTGRES_TEST_CONTAINER_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
//        super.stop();
    }

}