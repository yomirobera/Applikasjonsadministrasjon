package com.example.applikasjonsadministrasjon.services;

import java.util.Collection;
public interface CrudService <T, ID>  {
    T findById(ID id);
    Collection<T> findAll();
    T add(T entity);
    void update(T entity);
    void deleteById(ID id);
}
