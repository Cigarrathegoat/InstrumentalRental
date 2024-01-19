package br.com.instrumental_rental.service.interfaces;

import java.util.List;

public interface IService<T, E extends Throwable> {

    T save (T t);

    List<T> findAll();



}
