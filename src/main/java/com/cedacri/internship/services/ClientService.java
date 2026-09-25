package com.cedacri.internship.services;

import com.cedacri.internship.entities.Client;

public interface ClientService extends CrudOperations<Client> {

    double getBalance();

    boolean isValidForNewLoan();

}
