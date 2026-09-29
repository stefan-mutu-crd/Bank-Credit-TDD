package com.cedacri.internship.repositories.impl;

import javax.sql.DataSource;
import com.cedacri.internship.entities.Client;
import com.cedacri.internship.repositories.ClientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClientRepositoryImpl extends AbstractJDBCRepository<Client> implements ClientRepository {

    public ClientRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Client mapRow(ResultSet resultSet) throws SQLException {
        return  Client.builder()
                .id(resultSet.getInt("id"))
                .fullName(resultSet.getString("full_name"))
                .birthDate(LocalDate.parse(resultSet.getString("birth_date")))
                .build();
    }

    @Override
    protected String tableName() {
        return "clients";
    }

    @Override
    public void create(Client client) throws RuntimeException {
        String query = "INSERT INTO clients (full_name,birth_date,bank_id) VALUES (?,?,?)";
        try (var connection = dataSource.getConnection();
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
    public void update(Client client) throws RuntimeException {
        String query = """
                UPDATE clients
                SET full_name = ?,
                    birth_date  = ?
                WHERE id = ?;
                """;
        try (var connection = dataSource.getConnection();
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
}
