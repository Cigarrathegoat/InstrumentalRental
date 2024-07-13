package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import br.com.instrumental_rental.exceptions.PersonNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.models.ContactBuilder;
import br.com.instrumental_rental.repository.entities.Contact;
import br.com.instrumental_rental.repository.interfaces.IContactRepository;
import br.com.instrumental_rental.service.impl.ContactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ContactServiceTest {

    @Mock
    IContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSuccess() throws StoreNotFoundException, PersonNotFoundException, ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.save(builder)).thenReturn(builder);
        Contact save = contactService.save(builder);
        Assertions.assertEquals(builder, save);
    }

    @Test
    void testSaveFirstTimeSuccess() throws StoreNotFoundException, PersonNotFoundException, ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        var builderNoId = ContactBuilder.contactNoIdBuilder();
        when(contactRepository.save(builderNoId)).thenReturn(builder);
        List<Contact> savedList = contactService.saveFirstTime(List.of(builderNoId));
        Assertions.assertEquals(List.of(builder), savedList);
    }

    @Test
    void testFindSuccess() throws ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findById(builder.getContactId())).thenReturn(Optional.of(builder));
        Contact result = contactService.findById(builder.getContactId());
        Assertions.assertEquals(builder, result);
    }

    @Test
    void testFindContactNotFoundException() throws ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findById(builder.getContactId())).thenReturn(Optional.empty());
        ContactNotFoundException thrown = Assertions.assertThrows(ContactNotFoundException.class,
                () -> {contactService.findById(builder.getContactId());});
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Contact not found", thrown.getMessage());
    }

    @Test
    void testListAll() {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findAll()).thenReturn(List.of(builder));
        List<Contact> result = contactService.findAll();
        Assertions.assertEquals(List.of(builder), result);
    }

    @Test
    void testUpdateSuccess() throws ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findById(builder.getContactId())).thenReturn(Optional.of(builder));
        when(contactRepository.save(builder)).thenReturn(builder);
        Contact result = contactService.update(builder);
        Assertions.assertEquals(builder, result);
    }

    @Test
    void testUpdateContactNotFoundException() throws ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findById(builder.getContactId())).thenReturn(Optional.empty());
        ContactNotFoundException thrown = Assertions.assertThrows(ContactNotFoundException.class,
                () -> {contactService.update(builder);});
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Contact not found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findById(builder.getContactId())).thenReturn(Optional.of(builder));
        doNothing().when(contactRepository).delete(builder);
        contactService.delete(builder.getContactId());
        verify(contactRepository).findById(builder.getContactId());
        verify(contactRepository).delete(builder);
    }

    @Test
    void testDeleteContactNotFoundException() throws ContactNotFoundException {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.findById(builder.getContactId())).thenReturn(Optional.empty());
        ContactNotFoundException thrown = Assertions.assertThrows(ContactNotFoundException.class,
                () -> {contactService.delete(builder.getContactId());});
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Contact not found", thrown.getMessage());
    }
}
