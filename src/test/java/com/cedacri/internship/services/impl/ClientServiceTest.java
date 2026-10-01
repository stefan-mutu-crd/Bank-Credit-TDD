package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Client;
import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.BankRepository;
import com.cedacri.internship.repositories.impl.BankRepositoryImpl;
import com.cedacri.internship.repositories.impl.ClientRepositoryImpl;
import com.cedacri.internship.repositories.impl.LoanRepositoryImpl;
import com.cedacri.internship.services.BankService;
import com.cedacri.internship.services.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class ClientServiceTest extends TestConfig {

    private final ClientService clientService;
    private final BankRepository bankRepository;

    ClientServiceTest() {
        var clientRepository = new ClientRepositoryImpl(super.sessionFactory);
        var loanRepository = new LoanRepositoryImpl(super.sessionFactory);
        this.clientService = new ClientServiceImpl(clientRepository, loanRepository);
        this.bankRepository = new BankRepositoryImpl(super.sessionFactory);

    }

    @Test
    void getById_existingId_returnsBank() {
        Client client = clientService.findById(1);
        Assertions.assertEquals(1, client.getId());
        Assertions.assertEquals("Ștefan Mutu", client.getFullName());
        Assertions.assertEquals(LocalDate.of(1998, 8, 15), client.getBirthDate());
    }

    @Test
    void getById_nonExistingId_returnsNull() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> clientService.findById(999));
    }

    @Test
    void getAll_populatedDb_returnsAll() {
        List<Client> banks = clientService.findAll();
        Assertions.assertEquals(17, banks.size());
    }

    @Test
    void create_validInput_returnsNothing() {
        Client client = Client.builder()
                .fullName("Ghorghe Topa")
                .birthDate(LocalDate.of(1960, 1, 1))
                .bank(bankRepository.getById(1))
                .build();

        clientService.add(client);
        List<Client> banks = clientService.findAll();
        Assertions.assertEquals(18, banks.size());
    }

    @Test
    void create_invalidInput_throwsRuntimeException() {
        Client client = Client.builder()
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> clientService.add(client));
    }

    @Test
    void update_validInput_returnsNothing() {
        Client client = Client.builder()
                .id(1)
                .fullName("Ion Creanga")
                .birthDate(LocalDate.of(1860, 12, 1))
                .bank(bankRepository.getById(1))
                .build();

        clientService.edit(client);
        Client updatedClient = clientService.findById(1);
        Assertions.assertEquals(client.getId(), updatedClient.getId());
        Assertions.assertEquals(client.getFullName(), updatedClient.getFullName());
        Assertions.assertEquals(client.getBirthDate(), updatedClient.getBirthDate());
    }

    @Test
    void update_invalidInput_throwsRuntimeException() {
        Client client = Client.builder()
                .id(1)
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> clientService.add(client));
    }

    @Test
    void delete_existingId_notUsed_returnsNothing() {
        clientService.remove(17);
        Assertions.assertEquals(16, clientService.findAll().size());
        Assertions.assertThrows(RuntimeException.class, () -> clientService.findById(17));
    }

    @Test
    void delete_existingId_used_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> clientService.remove(1));
    }

    @Test
    void delete_wrongId_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> clientService.remove(999));
    }

    @Test
    void getBalance_NegativeBalance_returnsNegativeValue() {
        Assertions.assertTrue(clientService.getBalance(1) < 0);
    }

    @Test
    void getBalance_PositiveBalance_returnsNegativeValue() {
        Assertions.assertTrue(clientService.getBalance(3) > 0);
    }

    @Test
    void isValidForNewLoan_NegativeBalance_ReturnsTrue() {
        Assertions.assertFalse(clientService.isValidForNewLoan(1));
    }

    @Test
    void isValidForNewLoan_NegativeBalance_ReturnsFalse() {
        Assertions.assertTrue(clientService.isValidForNewLoan(3));
    }
}