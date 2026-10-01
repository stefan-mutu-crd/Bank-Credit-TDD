package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.impl.BankRepositoryImpl;
import com.cedacri.internship.repositories.impl.LoanRepositoryImpl;
import com.cedacri.internship.services.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BankServiceTest extends TestConfig {

    private final BankService bankService;

    BankServiceTest() {
        var bankRepository = new BankRepositoryImpl(super.sessionFactory);
        var loanRepository = new LoanRepositoryImpl(super.sessionFactory);
        this.bankService = new BankServiceImpl(bankRepository,loanRepository);
    }

    @Test
    void getById_existingId_returnsBank() {
        Bank bank = bankService.findById(1);
        Assertions.assertEquals(1, bank.getId());
        Assertions.assertEquals("Maib Centru", bank.getBranch());
        Assertions.assertEquals("Maib Centru", bank.getBranch());
        Assertions.assertEquals("blv. Ștefan cel Mare", bank.getAddress());
    }

    @Test
    void getById_nonExistingId_returnsNull() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> bankService.findById(999));
    }

    @Test
    void getAll_populatedDb_returnsAll() {
        List<Bank> banks = bankService.findAll();
        Assertions.assertEquals(5, banks.size());
    }


    @Test
    void create_validInput_returnsNothing() {
        Bank bank =  Bank.builder()
                .branch("Maib Poșta Veche")
                .address("str. Ceucari")
                .build();

        bankService.add(bank);
        List<Bank> banks = bankService.findAll();
        Assertions.assertEquals(6, banks.size());
    }

    @Test
    void create_invalidInput_throwsRuntimeException() {
        Bank bank = Bank.builder()
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> bankService.add(bank));
    }

    @Test
    void update_validInput_returnsNothing() {
        Bank bank = Bank.builder()
                .id(1)
                .branch("Centru New")
                .address("str. 31 august")
                .build();

        bankService.edit(bank);
        Bank updatedBank = bankService.findById(1);
        Assertions.assertEquals(bank.getId(), updatedBank.getId());
        Assertions.assertEquals(bank.getAddress(), updatedBank.getAddress());
    }

    @Test
    void update_invalidInput_throwsRuntimeException() {
        Bank bank = Bank.builder()
                .id(1)
                .address(null)
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> bankService.add(bank));
    }

    @Test
    void delete_existingId_notUsed_returnsNothing() {
        bankService.remove(5);
        Assertions.assertEquals(4, bankService.findAll().size());
        Assertions.assertThrows(RuntimeException.class, () -> bankService.findById(5));
    }

    @Test
    void delete_existingId_used_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> bankService.remove(1));
    }

    @Test
    void delete_wrongId_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> bankService.remove(999));
    }

    @Test
    void getBalance_NegativeBalance_returnsNegativeValue() {
        Assertions.assertTrue(bankService.getBalance(1) < 0);
    }

    @Test
    void getBalance_PositiveBalance_returnsNegativeValue() {
        Assertions.assertTrue(bankService.getBalance(3) > 0);
    }

    @Test
    void isProfitable_NegativeBalance_ReturnsTrue() {
        Assertions.assertFalse(bankService.isProfitable(1));
    }

    @Test
    void isProfitable_NegativeBalance_ReturnsFalse() {
        Assertions.assertTrue(bankService.isProfitable(3));
    }
}