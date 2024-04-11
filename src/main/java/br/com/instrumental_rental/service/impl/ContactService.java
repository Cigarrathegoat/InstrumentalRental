package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.ContactNotFoundException;
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

    public Contact findById(Long contactId) throws ContactNotFoundException {
        var contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("C01", "Contact not found"));
        return contact;
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
        var contactToUpdate = findById(contact.getContactId());
        contactToUpdate.setContactContent(contact.getContactContent());
        contactToUpdate.setContactType(contact.getContactType());
        contactRepository.save(contactToUpdate);
        return contactToUpdate;
    }

    @Override
    public void delete(Long id) throws ContactNotFoundException {
        var contactToDelete = findById(id);
        contactRepository.delete(contactToDelete);
    }

    @Override
    public List<Contact> findContactsByNameprovided(String name) {
        return contactRepository.findContactByNameProvided(name);
    }
}
