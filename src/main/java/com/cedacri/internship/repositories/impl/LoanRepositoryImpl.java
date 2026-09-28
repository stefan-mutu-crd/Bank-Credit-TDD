package com.cedacri.internship.repositories.impl;

import com.cedacri.internship.entities.Loan;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRepositoryImpl extends AbstractJDBCRepository<Loan> implements com.cedacri.internship.repositories.LoanRepository {

    public LoanRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Loan mapRow(ResultSet resultSet) throws SQLException {
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

    @Override
    protected String tableName() {
        return "loans";
    }

    @Override
    public void create(Loan loan) throws RuntimeException {
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
        try (var connection = dataSource.getConnection();
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
    public void update(Loan loan) throws RuntimeException {
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
        try (var connection = dataSource.getConnection();
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
}
