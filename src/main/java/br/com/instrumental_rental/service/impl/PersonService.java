package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.PersonNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Person;
import br.com.instrumental_rental.repository.interfaces.IPersonRepository;
import br.com.instrumental_rental.service.interfaces.IPersonService;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService implements IPersonService {

    IPersonRepository personRepository;

    IStoreService storeService;

    @Autowired
    public PersonService(IPersonRepository personRepository, IStoreService storeRepository) {
        this.personRepository = personRepository;
        this.storeService = storeRepository;
    }


    @Override
    public Person findById(Long Id) throws PersonNotFoundException, StoreNotFoundException {
        return personRepository.findById(Id).orElseThrow(() ->
                new PersonNotFoundException("P01", "Person Not Found"));
    }

}
