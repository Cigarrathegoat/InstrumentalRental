package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import br.com.instrumental_rental.repository.entities.Contact;

public class ContactDTOBuilder {

    public static ContactsDTO contactBuilder() {
        return ContactsDTO.builder()

                .contactsId(1L)
                .contactType("email")
                .contactContent("contact@gmail.com").build();
    }

    public static ContactsDTO contactNoIdBuilder() {
        return ContactsDTO.builder()
                .contactsId(null)
                .contactType("email")
                .contactContent("contact@gmail.com").build();
    }
}
