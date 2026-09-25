package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Client;
import com.cedacri.internship.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return List.of();
    }

    @Override
    public Client create(Client value) {
        return null;
    }

    @Override
    public Client update(Client value) {
        return null;
    }

    @Override
    public void delete(int id) {

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
