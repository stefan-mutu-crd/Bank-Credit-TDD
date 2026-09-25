package com.cedacri.internship.services;

import com.cedacri.internship.entities.Bank;

public interface BankService extends CrudOperations<Bank>{

    double getBalance();

    boolean isProfitable();
}
