package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.ClientRepository;
import com.cedacri.internship.repositories.impl.ClientRepositoryImpl;
import com.cedacri.internship.repositories.impl.LoanRepositoryImpl;
import com.cedacri.internship.services.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class LoanServiceTest extends TestConfig {

    private final LoanService loanService;
    private final ClientRepository clientRepository;

    LoanServiceTest() {
        var repository = new LoanRepositoryImpl(super.sessionFactory);
        loanService = new LoanServiceImpl(repository);
        this.clientRepository = new ClientRepositoryImpl(super.sessionFactory);
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
    void create_validInput_returnsNothing() {
        Loan loan = Loan.builder()
                .initialSum(Double.valueOf(1000))
                .refunded(Double.valueOf(1000))
                .purpose("Caruta")
                .dateOfIssue(LocalDate.of(2026, 5, 1))
                .deadline(LocalDate.of(2026, 5, 1))
                .percentage(0)
                .client(clientRepository.getById(1))
                .build();

        loanService.add(loan);
        List<Loan> banks = loanService.findAll();
        Assertions.assertEquals(20, banks.size());
    }

    @Test
    void create_invalidInput_throwsRuntimeException() {
        Loan loan = Loan.builder()
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> loanService.add(loan));
    }

    @Test
    void update_validInput_returnsNothing() {
        Loan loan = Loan.builder()
                .id(1)
                .initialSum(1000.0)
                .refunded(1000.0)
                .purpose("Moped")
                .dateOfIssue(LocalDate.of(2026, 5, 1))
                .deadline(LocalDate.of(2026, 5, 1))
                .percentage(0)
                .client(clientRepository.getById(1))
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
        Loan loan = Loan.builder()
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

    @Test
    void delete_wrongId_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> loanService.remove(999));
    }

    @Test
    void getBalance_paidOfLoan_returnPositiveNumber() {
        Loan loan = loanService.findById(1);
        Assertions.assertEquals(1000, loanService.getBalance(loan));
    }

    @Test
    void getBalance_unpaidLoan_returnNegative() {
        Loan loan = loanService.findById(2);
        Assertions.assertEquals(-3000, loanService.getBalance(loan));
    }

}