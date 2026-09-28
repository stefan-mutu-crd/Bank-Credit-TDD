package com.cedacri.internship.repositories.impl;

import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.repositories.BankRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRepositoryImpl extends AbstractJDBCRepository<Bank> implements BankRepository {

    public BankRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void create(Bank bank) throws RuntimeException {
        String query = "INSERT INTO banks (branch,address) VALUES (?,?)";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, bank.getBranch());
            statement.setString(2, bank.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bank bank) throws RuntimeException {
        String query = """
                UPDATE banks
                SET address = ?,
                    branch  = ?
                WHERE id = ?;
                """;
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, bank.getAddress());
            statement.setString(2, bank.getBranch());
            statement.setInt(3, bank.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Bank mapRow(ResultSet resultSet) throws SQLException {
        return new Bank.Builder()
                .id(resultSet.getInt("id"))
                .branch(resultSet.getString("branch"))
                .address(resultSet.getString("address"))
                .build();
    }

    @Override
    protected String tableName() {
        return "banks";
    }
}
