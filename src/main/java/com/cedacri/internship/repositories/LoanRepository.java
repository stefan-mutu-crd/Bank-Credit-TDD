package com.cedacri.internship.repositories;

import com.cedacri.internship.entities.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan> {

    List<Loan> findAllByClientId(int id);

    List<Loan> findAllByBankId(int id);
}
