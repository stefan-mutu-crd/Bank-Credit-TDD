package com.cedacri.internship.services.impl;

import com.cedacri.internship.config.DataSource;
import com.cedacri.internship.entities.Client;
import com.cedacri.internship.entities.Client;
import com.cedacri.internship.services.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest extends TestConfig{

    private final ClientService clientService;

    ClientServiceTest() {
        this.clientService = new ClientServiceImpl();
    }


    @Test
    void getById_existingId_returnsBank() {
        Client client = clientService.getById(1);
        Assertions.assertEquals(1, client.getId());
        Assertions.assertEquals("Ștefan Mutu", client.getFullName());
        Assertions.assertEquals(LocalDate.of(1998,8,15), client.getBirthDate());
    }

    @Test
    void getById_nonExistingId_returnsNull() {
        Client client = clientService.getById(999);
        Assertions.assertNull(client);
    }

    @Test
    void getAll_populatedDb_returnsAll() {
        List<Client> banks = clientService.getAll();
        Assertions.assertEquals(16, banks.size());
    }

    @Test
    void getAll_emptyDb_returnsEmpty() {
        clearDB();
        List<Client> banks = clientService.getAll();
        Assertions.assertTrue(banks.isEmpty());
    }

    @Test
    void create_validInput_returnsNothing() {
        Client client = new Client.Builder()
                .fullName("Ghorghe Topa")
                .birtDate(LocalDate.of(1960,1,1))
                .build();

        clientService.create(client);
        List<Client> banks = clientService.getAll();
        Assertions.assertEquals(5, banks.size());
    }

    @Test
    void create_invalidInput_throwsRuntimeException() {
        Client client = new Client.Builder()
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> clientService.create(client));
    }

    @Test
    void update_validInput_returnsNothing() {
        Client client = new Client.Builder()
                .id(1)
                .fullName("Ion Creanga")
                .birtDate(LocalDate.of(1860,12,1))
                .build();

        clientService.update(client);
        Client updatedClient = clientService.getById(1);
        Assertions.assertEquals(client.getId(), updatedClient.getId());
        Assertions.assertEquals(client.getFullName(), updatedClient.getFullName());
        Assertions.assertEquals(client.getBirthDate(), updatedClient.getBirthDate());
    }

    @Test
    void update_invalidInput_throwsRuntimeException() {
        Client client = new Client.Builder()
                .id(1)
                .build();
        Assertions.assertThrows(RuntimeException.class, () -> clientService.create(client));
    }

    @Test
    void delete_existingId_notUsed_returnsNothing() {
        clientService.delete(5);
        Assertions.assertEquals(4, clientService.getAll().size());
        Assertions.assertThrows(RuntimeException.class, () -> clientService.getById(5));
    }

    @Test
    void delete_existingId_used_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> clientService.delete(1));
    }

    @Test
    void delete_wrongId_throwsRuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> clientService.delete(999));
    }

    @Test
    void isValidForNewLoan() {
    }

    private void clearDB() {
        try (var connection = DataSource.getConnection();
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