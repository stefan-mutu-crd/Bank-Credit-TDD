package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSourceFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

public abstract class TestConfig {

    static DataSource dataSource;

    @BeforeAll
    static void init() {
        dataSource = DataSourceFactory.create(
                "jdbc:h2:C:/Users/crmp077/IdeaProjects/Bank Credit TDD/bank-system;AUTO_SERVER=TRUE");
    }

    @BeforeEach
    void initDB() {

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute("RUNSCRIPT FROM 'src/test/resources/init_db.sql'");

        } catch (Exception e) {

        }
    }

//    @AfterAll
//    static void closeDataSource() {
//        dataSource.d
//    }
}
