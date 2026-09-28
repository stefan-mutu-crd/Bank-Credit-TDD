package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.repositories.impl.LoanRepositoryImpl;
import com.cedacri.internship.services.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {

    private final LoanRepositoryImpl repository;

    public LoanServiceImpl(LoanRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Loan findById(int id) throws RuntimeException {
        return repository.getById(id);
    }

    @Override
    public List<Loan> findAll() {
        return repository.getAll();
    }

    @Override
    public void add(Loan loan) {
        repository.create(loan);
    }

    @Override
    public void edit(Loan loan) {
        findById(loan.getId());
        repository.update(loan);
    }

    @Override
    public void remove(int id) {
        findById(id);
        repository.delete(id);
    }

    @Override
    public double getBalance(Loan loan) {
        return loan.getRefunded()-loan.getInitialSum();
    }
}
