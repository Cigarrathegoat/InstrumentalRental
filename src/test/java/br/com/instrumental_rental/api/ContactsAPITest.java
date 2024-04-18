package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IContactsMapper;
import br.com.instrumental_rental.controller.api.ContactsAPI;
import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.ContactsListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.ContactsResponseDTO;
import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import br.com.instrumental_rental.models.ContactBuilder;
import br.com.instrumental_rental.models.ContactDTOBuilder;
import br.com.instrumental_rental.service.interfaces.IContactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class ContactsAPITest {
    @Mock
    IContactService contactService;

    @Mock
    IContactsMapper contactMapper;

    @InjectMocks
    ContactsAPI contactsAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testAddSuccess() {
        var contactNoId = ContactBuilder.contactNoIdBuilder();
        var contactBuilder = ContactBuilder.contactBuilder();
        var contactDTONoIdBuilder = ContactDTOBuilder.contactNoIdBuilder();
        var contactDTOBuilder = ContactDTOBuilder.contactBuilder();

        when(contactMapper.convertToEntity(contactDTONoIdBuilder)).thenReturn(contactNoId);
        when(contactService.save(contactNoId)).thenReturn(contactBuilder);
        when(contactMapper.convertToDTO(contactBuilder)).thenReturn(contactDTOBuilder);
        ContactsResponseDTO result = contactsAPI.add(contactDTONoIdBuilder);
        Assertions.assertEquals(ContactsResponseDTO.builder().data(contactDTOBuilder).build(), result);
    }

    @Test
    void testFindSuccess() throws ContactNotFoundException {
        var contactBuilder = ContactBuilder.contactBuilder();
        var contactDTOBuilder = ContactDTOBuilder.contactBuilder();
        when(contactService.findContactsByNameProvided(contactBuilder.getContactName()))
                .thenReturn(List.of(contactBuilder));
        when(contactMapper.convertToDTOList(List.of(contactBuilder)))
                .thenReturn(List.of(contactDTOBuilder));
        ContactsListResponseDTO result = contactsAPI.find(contactBuilder.getContactName());
        Assertions.assertEquals(ContactsListResponseDTO.builder()
                .data(List.of(contactDTOBuilder)).build(), result);
    }

    @Test
    void testFindContactNotFoundException() throws ContactNotFoundException {
        var contactBuilder = ContactBuilder.contactBuilder();
        when(contactService.findContactsByNameProvided(contactBuilder.getContactName()))
                .thenThrow(new ContactNotFoundException("C01", "Contact not found"));
        ContactNotFoundException thrown = Assertions.assertThrows(
                ContactNotFoundException.class, () -> {
                    contactsAPI.find(contactBuilder.getContactName());
                }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Contact not found", thrown.getMessage());
    }

    @Test
    void testUpdateSuccess() throws ContactNotFoundException {
        var contactBuilder = ContactBuilder.contactBuilder();
        var contactDTOBuilder = ContactDTOBuilder.contactBuilder();
        when(contactService.findById(contactBuilder.getContactId())).thenReturn(contactBuilder);
        when(contactService.update(contactBuilder)).thenReturn(contactBuilder);
        when(contactMapper.convertToDTO(contactBuilder)).thenReturn(contactDTOBuilder);
        ContactsResponseDTO result = contactsAPI.update(contactBuilder.getContactId(), contactDTOBuilder);
        Assertions.assertEquals(ContactsResponseDTO.builder().data(contactDTOBuilder).build(), result);
    }

    @Test
    void testUpdateContactNotFoundException() throws ContactNotFoundException {
        var contactDTOBuilder = ContactDTOBuilder.contactBuilder();
        when(contactService.findById(contactDTOBuilder.getContactsId())).thenThrow(
                new ContactNotFoundException("C01", "Contact not found")
        );
        ContactNotFoundException thrown = Assertions.assertThrows(ContactNotFoundException.class,
                () -> {contactsAPI.update(contactDTOBuilder.getContactsId(), contactDTOBuilder);
        }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Contact not found", thrown.getMessage());
    }

    /*@Test
    void deleteSuccess() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        doNothing().when(instrumentService).delete(instrument.getInstrumentId());
        instrumentAPI.delete(instrument.getInstrumentId());
        verify(instrumentService, times(1)).delete(instrument.getInstrumentId());
    }

     */
    @Test
    void testDeleteSuccess() throws ContactNotFoundException {
        var contactDTOBuilder = ContactDTOBuilder.contactBuilder();
        var contactBuilder = ContactBuilder.contactBuilder();
        doNothing().when(contactService).delete(contactBuilder.getContactId());
        contactsAPI.delete(contactBuilder.getContactId());
        verify(contactService, times(1)).delete(contactBuilder.getContactId());
    }
}
