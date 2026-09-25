package com.cedacri.internship;

import com.cedacri.internship.config.DataSource;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
////        try (var connection = DriverManager.getConnection("jdbc:h2:./bank-system;AUTO_SERVER=TRUE");
//        try (var connection = DriverManager.getConnection("jdbc:h2:./bank-system;AUTO_SERVER=TRUE");
//             var statement = connection.createStatement()) {
//
//            String query = "SELECT * FROM banks;";
//
//            var resultSet = statement.executeQuery(query);
//
//            while(resultSet.next()){
//                System.out.println(resultSet.getString("branch"));
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }

        try {
            var connection = DataSource.getConnection();
            var statement = connection.createStatement();
            String query = "SELECT * FROM banks;";

            var resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("branch"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}