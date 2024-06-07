package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import br.com.instrumental_rental.exceptions.PersonNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;

import java.util.List;

public interface IService<T, E extends Throwable> {

    T save (T t) throws StoreNotFoundException, PersonNotFoundException, ContactNotFoundException;

    List<T> findAll();

    T update (T t) throws E;

    void delete (Long id) throws E;



}
