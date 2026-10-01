package com.cedacri.internship.services.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Statement;

abstract class TestConfig {

    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15-alpine");

    static final SessionFactory sessionFactory;
    static final String initScript;

    static {
        postgres.start();

        sessionFactory = new Configuration()
                .configure()
                .setProperty("hibernate.connection.url", postgres.getJdbcUrl())
                .setProperty("hibernate.connection.username", postgres.getUsername())
                .setProperty("hibernate.connection.password", postgres.getPassword())
                .setProperty("hibernate.hbm2ddl.auto", "none")
                .setProperty("hibernate.physical_naming_strategy",
                        "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy")
                .buildSessionFactory();

        try (InputStream in = TestConfig.class.getResourceAsStream("/init_db.sql")) {
            initScript = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException | NullPointerException e) {
            throw new IllegalStateException("Could not read /init_db.sql", e);
        }
    }

    @BeforeEach
    void resetDatabase() {
        sessionFactory.inTransaction(session ->
                session.doWork(connection -> {
                    try (Statement st = connection.createStatement()) {
                        st.execute(initScript);
                    }
                }));
    }
}
