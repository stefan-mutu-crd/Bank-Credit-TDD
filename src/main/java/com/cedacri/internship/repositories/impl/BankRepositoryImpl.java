package com.cedacri.internship.repositories.impl;

import com.cedacri.internship.entities.Bank;
import com.cedacri.internship.repositories.BankRepository;
import org.hibernate.SessionFactory;

public class BankRepositoryImpl extends AbstractHibernateRepository<Bank> implements BankRepository {

    public BankRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Bank.class);
    }
}
