package com.cedacri.internship.services;

import com.cedacri.internship.entities.Loan;

public interface LoanService extends CrudOperations<Loan> {

    double getBalance();

}
