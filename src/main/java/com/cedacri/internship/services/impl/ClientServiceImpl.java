package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Client;
import com.cedacri.internship.repositories.ClientRepository;
import com.cedacri.internship.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client findById(int id) throws RuntimeException {
        return repository.getById(id);
    }

    @Override
    public List<Client> findAll() {
        return repository.getAll();
    }

    @Override
    public void add(Client client) {
        repository.create(client);
    }

    @Override
    public void edit(Client client) {
        findById(client.getId());
        repository.update(client);
    }

    @Override
    public void remove(int id) {
        findById(id);
        repository.delete(id);
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public boolean isValidForNewLoan() {
        return false;
    }
}
