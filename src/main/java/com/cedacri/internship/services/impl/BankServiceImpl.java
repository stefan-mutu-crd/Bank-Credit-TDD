package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.services.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    @Override
    public Bank getById(int id) {
        return null;
    }

    @Override
    public List<Bank> getAll() {
        return List.of();
    }

    @Override
    public Bank create(Bank value) {
        return null;
    }

    @Override
    public Bank update(Bank value) {
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
    public boolean isProfitable() {
        return false;
    }
}
