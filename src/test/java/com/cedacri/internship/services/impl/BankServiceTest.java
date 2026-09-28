package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.impl.BankRepositoryImpl;
import com.cedacri.internship.services.BankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BankServiceTest extends TestConfig {

    private final BankService bankService;

    BankServiceTest() {
        var repository = new BankRepositoryImpl(super.dataSource);
        this.bankService = new BankServiceImpl(repository);
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
    void getAll_emptyDb_returnsEmpty() {
        clearDB();
        List<Bank> banks = bankService.findAll();
        Assertions.assertTrue(banks.isEmpty());
    }

    @Test
    void create_validInput_returnsNothing() {
        Bank bank = new Bank.Builder()
                .branch("Maib Poșta Veche")
                .address("str. Ceucari")
                .build();

        bankService.add(bank);
        List<Bank> banks = bankService.findAll();
        Assertions.assertEquals(6, banks.size());
    }

    @Test
    void create_invalidInput_throwsRuntimeException() {
        Bank bank = new Bank.Builder()
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> bankService.add(bank));
    }

    @Test
    void update_validInput_returnsNothing() {
        Bank bank = new Bank.Builder()
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
        Bank bank = new Bank.Builder()
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
    void getBalance() {
    }

    @Test
    void isProfitable() {
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
        ;
    }
}