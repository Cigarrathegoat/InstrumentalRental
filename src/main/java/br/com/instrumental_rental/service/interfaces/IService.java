package br.com.instrumental_rental.service.interfaces;

import java.util.List;

public interface IService<T, E extends Throwable> {

    T save (T t) throws E;

    void delete(T t) throws E;

    List<T> findAll();

    T update(T t) throws E;
}
