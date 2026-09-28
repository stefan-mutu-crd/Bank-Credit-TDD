package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.repositories.BankRepository;
import com.cedacri.internship.services.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank findById(int id) throws RuntimeException {
        return bankRepository.getById(id);
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.getAll();
    }

    @Override
    public void add(Bank bank) {
        bankRepository.create(bank);
    }

    @Override
    public void edit(Bank bank) {
        findById(bank.getId());
        bankRepository.update(bank);
    }

    @Override
    public void remove(int id) {
        findById(id);
        bankRepository.delete(id);
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
