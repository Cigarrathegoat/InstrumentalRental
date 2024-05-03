package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.PersonNotFoundException;
import br.com.instrumental_rental.repository.entities.Person;
import br.com.instrumental_rental.repository.interfaces.IPersonRepository;
import br.com.instrumental_rental.service.interfaces.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.nio.file.Paths.get;

@Service
@Slf4j
public class PersonService implements IPersonService {

    IPersonRepository personRepository;

    @Autowired
    public PersonService(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person findById(Long personId) throws PersonNotFoundException {
        return personRepository.findById(personId).orElseThrow(() ->
                new PersonNotFoundException("P01", "Person not found")
        );
    }

    @Override
    public Person findByIdWithoutExceptionThrown(Long personId) {
        return get(personRepository.findById(personId));
    }
}
