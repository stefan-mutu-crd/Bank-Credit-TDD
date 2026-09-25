package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.services.BankService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankServiceImpl implements BankService {
    @Override
    public Bank getById(int id) throws RuntimeException {
        String query = "SELECT * FROM banks WHERE id=?";
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBank(resultSet);
                } else {
                    throw new ResourceNotFoundException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bank> getAll() {
        String query = "SELECT * FROM banks";
        List<Bank> banks = new ArrayList<>();
        try (var connection = DataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                banks.add(mapResultSetToBank(resultSet));
            }
            return banks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Bank bank) {
        String query = "INSERT INTO banks (branch,address) VALUES (?,?)";
        try (var connection = DataSource.getConnection();
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
    public void update(Bank bank) {

        getById(bank.getId());

        String query = """
                UPDATE banks
                SET address = ?,
                    branch  = ?
                WHERE id = ?;
                """;
        try (var connection = DataSource.getConnection();
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
    public void delete(int id) {

        getById(id);

        String query = """
                DELETE FROM banks WHERE id = ?;
                """;
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public boolean isProfitable() {
        return false;
    }

    private Bank mapResultSetToBank(ResultSet resultSet) throws SQLException {
        return new Bank.Builder()
                .id(resultSet.getInt("id"))
                .branch(resultSet.getString("branch"))
                .address(resultSet.getString("address"))
                .build();
    }
}
