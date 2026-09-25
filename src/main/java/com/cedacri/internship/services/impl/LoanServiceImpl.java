package com.cedacri.internship.services.impl;

import com.cedacri.internship.entities.Loan;
import com.cedacri.internship.services.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {
    @Override
    public Loan getById(int id) {
        return null;
    }

    @Override
    public List<Loan> getAll() {
        return List.of();
    }

    @Override
    public void create(Loan value) {
    }

    @Override
    public void update(Loan value) {
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public double getBalance() {
        return 0;
    }
}
