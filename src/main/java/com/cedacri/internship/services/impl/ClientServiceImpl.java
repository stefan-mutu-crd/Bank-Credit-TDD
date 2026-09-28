package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Client;
import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.repositories.ClientRepository;
import com.cedacri.internship.repositories.LoanRepository;
import com.cedacri.internship.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;

    public ClientServiceImpl(ClientRepository repository,
                             LoanRepository loanRepository) {
        this.clientRepository = repository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Client findById(int id) throws RuntimeException {
        return clientRepository.getById(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.getAll();
    }

    @Override
    public void add(Client client) {
        clientRepository.create(client);
    }

    @Override
    public void edit(Client client) {
        findById(client.getId());
        clientRepository.update(client);
    }

    @Override
    public void remove(int id) {
        findById(id);
        clientRepository.delete(id);
    }

    @Override
    public double getBalance(int clientId) {
        List<Loan> loans = loanRepository.findAllByClientId(clientId);
        return loans.stream().mapToDouble(loan ->
                loan.getRefunded() - loan.getInitialSum()
        ).sum();
    }

    @Override
    public boolean isValidForNewLoan(int clientId) {
        return getBalance(clientId) >= 0;
    }
}
