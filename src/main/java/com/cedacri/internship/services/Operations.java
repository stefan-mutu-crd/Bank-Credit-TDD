package com.cedacri.internship.services;

import java.util.List;

public interface Operations<T> {

    T findById(int id) throws RuntimeException;

    List<T> findAll() throws RuntimeException;

    void add(T value) throws RuntimeException;

    void edit(T value) throws RuntimeException;

    void remove(int id) throws RuntimeException;
}
