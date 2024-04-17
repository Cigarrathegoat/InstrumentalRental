package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IContactsMapper;
import br.com.instrumental_rental.controller.api.ContactsAPI;
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

import static org.mockito.Mockito.when;

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

    /*@Test
    void testAddSuccess() {
        var attendantNoId = AttendantBuilder.attendantBuilderNoId();
        var attendantBuilder = AttendantBuilder.attendantBuilder();
        var attendantDTONoIdBuilder = AttendantDTOBuilder.attendantDTONoIdSuccessBuilder();
        var attendantDTOBuilder = AttendantDTOBuilder.attendantDTOSuccessBuilder();
        when(attendantMapper.convertToEntity(attendantDTONoIdBuilder)).thenReturn(attendantNoId);
        when(attendantService.save(attendantNoId)).thenReturn(attendantBuilder);
        when(attendantMapper.convertToDTO(attendantBuilder)).thenReturn(attendantDTOBuilder);
        AttendantResponseDTO result = attendantAPI.add(attendantDTONoIdBuilder);
        Assertions.assertEquals(AttendantResponseDTO.builder().data(attendantDTOBuilder).build(), result);
    }
     */
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
        when(contactService.findContactsByNameProvided(contactBuilder.()))
    }
}
