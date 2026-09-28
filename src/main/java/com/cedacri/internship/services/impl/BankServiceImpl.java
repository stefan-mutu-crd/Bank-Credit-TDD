package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.repositories.BankRepository;
import com.cedacri.internship.repositories.LoanRepository;
import com.cedacri.internship.services.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final LoanRepository loanRepository;

    public BankServiceImpl(BankRepository bankRepository,
                           LoanRepository loanRepository) {
        this.bankRepository = bankRepository;
        this.loanRepository = loanRepository;
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
    public double getBalance(int bankId) {
        return loanRepository.findAllByBankId(bankId)
                .stream()
                .mapToDouble(loan -> loan.getRefunded() - loan.getInitialSum())
                .sum();

    }

    @Override
    public boolean isProfitable(int bankId) {
        return getBalance(bankId) >= 0;
    }
}
