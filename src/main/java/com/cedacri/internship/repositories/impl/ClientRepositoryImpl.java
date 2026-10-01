package com.cedacri.internship.repositories.impl;

import com.cedacri.internship.entities.Client;
import com.cedacri.internship.repositories.ClientRepository;
import org.hibernate.SessionFactory;

public class ClientRepositoryImpl extends AbstractHibernateRepository<Client> implements ClientRepository {

    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Client.class);
    }
}
