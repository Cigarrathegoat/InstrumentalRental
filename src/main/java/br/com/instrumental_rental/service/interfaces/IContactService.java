package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.repository.entities.Contact;

import java.util.List;

public interface IContactService extends IService<Contact, Exception> {

    List<Contact> findContactsByNameprovided(String name);
}
