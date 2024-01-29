package br.com.instrumental_rental.service;

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

import static org.mockito.Mockito.when;

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
    void testSaveSuccess() {
        var builder = ContactBuilder.contactBuilder();
        when(contactRepository.save(builder)).thenReturn(builder);
        Contact save = contactService.save(builder);
        Assertions.assertEquals(builder, save);
    }
}
