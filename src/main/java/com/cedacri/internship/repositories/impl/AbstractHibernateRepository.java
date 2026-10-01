package com.cedacri.internship.repositories.impl;

import com.cedacri.internship.exceptions.ResourceNotFoundException;
import com.cedacri.internship.repositories.CrudRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class AbstractHibernateRepository<T> implements CrudRepository<T> {

    private final SessionFactory sessionFactory;

    private final Class<T> entityClass;

    public AbstractHibernateRepository(SessionFactory sessionFactory,
                                       Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public T getById(int id) throws RuntimeException {
        T value =
         sessionFactory.fromSession(session ->
                session.find(entityClass, id));
        if(value != null){
            return value;
        }
        throw new ResourceNotFoundException();
    }

    @Override
    public List<T> getAll() throws RuntimeException {
        return sessionFactory.fromSession(session ->
                session.createSelectionQuery("FROM " + entityClass.getSimpleName(), entityClass)
                        .getResultList());
    }

    @Override
    public void create(T value) throws RuntimeException {
        sessionFactory.inTransaction(session ->
                session.persist(value));
    }

    @Override
    public void update(T value) throws RuntimeException {
        sessionFactory.inTransaction(session ->
                session.merge(value));
    }

    @Override
    public void delete(int id) throws RuntimeException {
        sessionFactory.inTransaction(session ->
        {
            T value = session.find(entityClass, id);
            if (value == null) {
                throw new RuntimeException();
            }
            session.remove(value);
        });
    }
}
