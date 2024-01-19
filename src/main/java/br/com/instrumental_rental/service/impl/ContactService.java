package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.repository.entities.Contact;
import br.com.instrumental_rental.repository.interfaces.IContactRepository;
import br.com.instrumental_rental.service.interfaces.IContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContactService implements IContactService {

    IContactRepository contactRepository;

    @Autowired
    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact update(Contact contact) throws ContactNotFoundException {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
