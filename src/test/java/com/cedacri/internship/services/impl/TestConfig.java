package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

public abstract class TestConfig {

    @BeforeEach
    void initDB() throws SQLException {
        try (var connection = DataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute("RUNSCRIPT FROM 'src/test/resources/init_db.sql'");
        }
    }
}
