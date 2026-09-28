package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.services.LoanService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl implements LoanService {
    @Override
    public Loan getById(int id) throws RuntimeException {
        String query = "SELECT * FROM loans WHERE id=?";
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (var resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToLoan(resultSet);
                } else {
                    throw new ResourceNotFoundException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Loan> getAll() {
        String query = "SELECT * FROM loans";
        List<Loan> loans = new ArrayList<>();
        try (var connection = DataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                loans.add(mapResultSetToLoan(resultSet));
            }
            return loans;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Loan loan) {
        String query = """
                INSERT INTO loans (initial_sum,
                                  refunded,
                                  purpose,
                                  date_of_issue,
                                  deadline,
                                  percentage,
                                  client_id)
                VALUES (?,?,?,?,?,?,?)
                """;
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setDouble(1, loan.getInitialSum());
            statement.setDouble(2, loan.getRefunded());
            statement.setString(3, loan.getPurpose());
            statement.setObject(4, loan.getDateOfIssue());
            statement.setObject(5, loan.getDeadline());
            statement.setInt(6, loan.getPercentage());
            statement.setInt(7, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Loan loan) {

        getById(loan.getId());

        String query = """
                UPDATE loans
                   SET initial_sum = ?,
                                  refunded = ?,
                                  purpose = ?,
                                  date_of_issue = ?,
                                  deadline = ?,
                                  percentage = ?
                WHERE id = ?;
                """;
        try (var connection = DataSource.getConnection();
             var statement = connection.prepareStatement(query);
        ) {
            statement.setDouble(1, loan.getInitialSum());
            statement.setDouble(2, loan.getRefunded());
            statement.setString(3, loan.getPurpose());
            statement.setObject(4, loan.getDateOfIssue());
            statement.setObject(5, loan.getDeadline());
            statement.setInt(6, loan.getPercentage());
            statement.setInt(7, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

        getById(id);

        String query = "DELETE FROM loans WHERE id = ?;";
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

    private Loan mapResultSetToLoan(ResultSet resultSet) throws SQLException {
        return new Loan.Builder()
                .id(resultSet.getInt("id"))
                .initialSum(resultSet.getDouble("initial_sum"))
                .refunded(resultSet.getDouble("refunded"))
                .purpose(resultSet.getString("purpose"))
                .dateOfIssue(resultSet.getDate("date_of_issue").toLocalDate())
                .deadline(resultSet.getDate("deadline").toLocalDate())
                .percentage(resultSet.getInt("percentage"))
                .build();
    }

}
