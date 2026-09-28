package com.cedacri.internship.repositories.impl;

import javax.sql.DataSource;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.CrudRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJDBCRepository<T> implements CrudRepository<T> {

    protected final DataSource dataSource;

    protected AbstractJDBCRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected abstract T mapRow(ResultSet resultSet) throws SQLException;

    protected abstract String tableName();

    @Override
    public T getById(int id) throws RuntimeException {
        String query = "SELECT * FROM " + tableName() + " WHERE id=?";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                } else {
                    throw new ResourceNotFoundException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> getAll() throws RuntimeException {
        String query = "SELECT * FROM " + tableName();
        List<T> banks = new ArrayList<>();
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                banks.add(mapRow(resultSet));
            }
            return banks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws RuntimeException {
        String query = "DELETE FROM " + tableName() + " WHERE id = ?;";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
