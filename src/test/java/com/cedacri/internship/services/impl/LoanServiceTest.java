package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.impl.LoanRepositoryImpl;
import com.cedacri.internship.services.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class LoanServiceTest extends TestConfig {

    private final LoanService loanService;

    LoanServiceTest() {
        var repository = new LoanRepositoryImpl(super.dataSource);
        loanService = new LoanServiceImpl(repository);
    }

    @Test
    void getById_existingId_returnsBank() {
        Loan loan = loanService.findById(1);
        Assertions.assertEquals(1, loan.getId());
        Assertions.assertEquals(10000, loan.getInitialSum());
        Assertions.assertEquals(11000, loan.getRefunded());
        Assertions.assertEquals("Achiziționare frigider", loan.getPurpose());
        Assertions.assertEquals(LocalDate.of(2025, 5, 1), loan.getDateOfIssue());
        Assertions.assertEquals(LocalDate.of(2026, 5, 1), loan.getDeadline());
        Assertions.assertEquals(10, loan.getPercentage());
    }

    @Test
    void getById_nonExistingId_returnsNull() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> loanService.findById(999));
    }

    @Test
    void getAll_populatedDb_returnsAll() {
        List<Loan> banks = loanService.findAll();
        Assertions.assertEquals(19, banks.size());
    }

    @Test
    void getAll_emptyDb_returnsEmpty() {
        clearDB();
        List<Loan> banks = loanService.findAll();
        Assertions.assertTrue(banks.isEmpty());
    }

    @Test
    void create_validInput_returnsNothing() {
        Loan loan = new Loan.Builder()
                .initialSum(1000)
                .refunded(1000)
                .purpose("Caruta")
                .dateOfIssue(LocalDate.of(2026, 5, 1))
                .deadline(LocalDate.of(2026, 5, 1))
                .percentage(0)
                .build();

        loanService.add(loan);
        List<Loan> banks = loanService.findAll();
        Assertions.assertEquals(20, banks.size());
    }

    @Test
    void create_invalidInput_throwsRuntimeException() {
        Loan loan = new Loan.Builder()
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> loanService.add(loan));
    }

    @Test
    void update_validInput_returnsNothing() {
        Loan loan = new Loan.Builder()
                .id(1)
                .initialSum(1000)
                .refunded(1000)
                .purpose("Moped")
                .dateOfIssue(LocalDate.of(2026, 5, 1))
                .deadline(LocalDate.of(2026, 5, 1))
                .percentage(0)
                .build();

        loanService.edit(loan);
        Loan updatedLoan = loanService.findById(1);
        Assertions.assertEquals(loan.getId(), updatedLoan.getId());
        Assertions.assertEquals(loan.getInitialSum(), updatedLoan.getInitialSum());
        Assertions.assertEquals(loan.getDeadline(), updatedLoan.getDeadline());
        Assertions.assertEquals(loan.getRefunded(), updatedLoan.getRefunded());
    }

    @Test
    void update_invalidInput_throwsRuntimeException() {
        Loan loan = new Loan.Builder()
                .id(1)
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> loanService.add(loan));
    }

    @Test
    void delete_existingId_notUsed_returnsNothing() {
        loanService.remove(5);
        Assertions.assertEquals(18, loanService.findAll().size());
        Assertions.assertThrows(RuntimeException.class, () -> loanService.findById(5));
    }

//    @Test
//    void delete_existingId_used_throwsRuntimeException() {
//        Assertions.assertThrows(RuntimeException.class, () -> loanService.delete(1));
//    }

    @Test
    void delete_wrongId_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> loanService.remove(999));
    }

    @Test
    void getBalance() {
    }

    private void clearDB() {
        try (var connection = new DataSource().getConnection();
             var statement = connection.createStatement();
        ) {
            statement.execute("DELETE  FROM loans WHERE id IS NOT NULL;" +
                    "DELETE  FROM clients WHERE id IS NOT NULL;" +
                    "DELETE  FROM banks WHERE id IS NOT NULL;");
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}