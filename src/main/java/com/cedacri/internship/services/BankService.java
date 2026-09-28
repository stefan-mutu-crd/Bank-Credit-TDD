package com.cedacri.internship.services;

import com.cedacri.internship.entities.Bank;

public interface BankService extends Operations<Bank> {

    double getBalance();

    boolean isProfitable();
}
