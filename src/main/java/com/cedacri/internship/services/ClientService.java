package com.cedacri.internship.services;

import com.cedacri.internship.entities.Client;

public interface ClientService extends Operations<Client> {

    double getBalance(int clientId);

    boolean isValidForNewLoan(int clientId);

}
