package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.PersonNotFoundException;
import br.com.instrumental_rental.repository.entities.Person;

public interface IPersonService {

    Person findById(Long personId) throws PersonNotFoundException;
}
