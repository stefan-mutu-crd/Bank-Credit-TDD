package com.cedacri.internship.services;

import java.util.List;

public interface CrudOperations<T> {

    T getById(int id) throws RuntimeException;

    List<T> getAll() throws RuntimeException;

    void create(T value) throws RuntimeException;

    void update(T value) throws RuntimeException;

    void delete(int id) throws RuntimeException;
}
