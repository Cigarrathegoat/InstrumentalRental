package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Contact;

import java.util.List;

public interface IContactService extends IService<Contact, Exception> {

    Contact save (Contact contact);

    List<Contact> saveFirstTime(List<Contact> contactList);

    List<Contact> findAll();

    Contact update (Contact contact) throws ContactNotFoundException;

    void delete (Long id) throws ContactNotFoundException;

    List<Contact> findContactsByNameProvided(String name) throws ContactNotFoundException;

    Contact findById(Long contactId) throws ContactNotFoundException;

    void addToPersonOrStore(Long contactId, Long personOrStoreId)
            throws ContactNotFoundException, StoreNotFoundException;
}
