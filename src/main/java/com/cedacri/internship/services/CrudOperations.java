package com.cedacri.internship.services;

import java.util.List;

public interface CrudOperations<T> {

    T getById(int id);

    List<T> getAll();

    T create(T value);

    T update(T value);

    void delete(int id);

}
