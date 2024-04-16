package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IContactsMapper;
import br.com.instrumental_rental.controller.api.ContactsAPI;
import br.com.instrumental_rental.service.interfaces.IContactService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ContactsAPITest {
    @Mock
    IContactService contactService;

    @Mock
    IContactsMapper contactMapper;

    @InjectMocks
    ContactsAPI contactsAPI;
}
