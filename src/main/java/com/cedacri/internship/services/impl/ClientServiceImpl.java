package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Client;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.services.ClientService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public Client getById(int id) throws RuntimeException {
        String query = "SELECT * FROM clients WHERE id=?";
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToClient(resultSet);
                } else {
                    throw new ResourceNotFoundException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> getAll() {
        String query = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try (var connection = DataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                clients.add(mapResultSetToClient(resultSet));
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Client client) {
        String query = "INSERT INTO clients (full_name,birth_date,bank_id) VALUES (?,?,?)";
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, client.getFullName());
            statement.setObject(2, client.getBirthDate());
            statement.setInt(3, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Client client) {

        getById(client.getId());

        String query = """
                UPDATE clients
                SET full_name = ?,
                    birth_date  = ?
                WHERE id = ?;
                """;
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, client.getFullName());
            statement.setObject(2, client.getBirthDate());
            statement.setInt(3, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

        getById(id);

        String query = "DELETE FROM clients WHERE id = ?;";
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
    public boolean isValidForNewLoan() {
        return false;
    }

    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        return new Client.Builder()
                .id(resultSet.getInt("id"))
                .fullName(resultSet.getString("full_name"))
                .birtDate(LocalDate.parse(resultSet.getString("birth_date")))
                .build();
    }
}
