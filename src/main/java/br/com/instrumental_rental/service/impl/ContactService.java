package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import br.com.instrumental_rental.exceptions.PersonNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Contact;
import br.com.instrumental_rental.repository.entities.Person;
import br.com.instrumental_rental.repository.entities.Store;
import br.com.instrumental_rental.repository.interfaces.IContactRepository;
import br.com.instrumental_rental.service.interfaces.IContactService;
import br.com.instrumental_rental.service.interfaces.IPersonService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContactService implements IContactService {

    IContactRepository contactRepository;

    IStoreService storeService;

    IPersonService personService;

    @Autowired
    public ContactService(IContactRepository contactRepository, IStoreService storeService,
                          IPersonService personService) {
        this.contactRepository = contactRepository;
        this.storeService = storeService;
        this.personService = personService;
    }

    public Contact findById(Long contactId) throws ContactNotFoundException {
        var contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("C01", "Contact not found"));
        return contact;
    }

    @Override
    public void addToPersonOrStore(Long contactId, Long personOrStoreId)
            throws ContactNotFoundException, StoreNotFoundException, PersonNotFoundException {
        try {
            var person = personService.findById(personOrStoreId);
            person.getContacts().add(findById(contactId));
        } catch (PersonNotFoundException e) {
            var store = storeService.findById(personOrStoreId);
            store.getContacts().add(findById(contactId));
        }
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> saveFirstTime(List<Contact> contactList) {
        List<Contact> savedContacts = new ArrayList<>();
        for (Contact contact : contactList) {
            Contact savedContact = contactRepository.save(contact);
            savedContacts.add(savedContact);
        }
        return savedContacts;
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
    public List<Contact> findContactsByNameProvided(String name) throws ContactNotFoundException {
        var contactFound = contactRepository.findContactByNameProvided(name);
        if (contactFound.isEmpty()) {
            throw new ContactNotFoundException("C01", "contact not found");
        }
        return contactFound;
    }
}
