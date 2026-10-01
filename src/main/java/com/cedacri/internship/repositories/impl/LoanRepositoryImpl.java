package com.cedacri.internship.repositories.impl;

import com.cedacri.internship.entities.Loan;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoanRepositoryImpl extends AbstractHibernateRepository<Loan> implements com.cedacri.internship.repositories.LoanRepository {

    private final SessionFactory sessionFactory;

    public LoanRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Loan.class);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Loan> findAllByClientId(int id) {
        return sessionFactory.fromSession(session ->
                session.createSelectionQuery("FROM Loan WHERE client.id = :clientId", Loan.class)
                        .setParameter("clientId", id)
                        .getResultList());
    }

    @Override
    public List<Loan> findAllByBankId(int id) {
        return sessionFactory.fromSession(session ->
                session.createSelectionQuery(
                                "FROM Loan l WHERE l.client.bank.id = :id", Loan.class)
                        .setParameter("id", id)
                        .getResultList());
    }
}
