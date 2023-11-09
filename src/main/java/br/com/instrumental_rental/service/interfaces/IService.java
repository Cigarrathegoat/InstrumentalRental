package br.com.instrumental_rental.service.interfaces;

import java.util.List;

public interface IService<T, E extends Exception> {

    T save (T t);

    void delete(T t) throws E;

    List<T> findAll();

    T update(T t) throws E;
}
