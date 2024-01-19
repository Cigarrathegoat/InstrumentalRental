package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Contact;

public class ContactBuilder {

    public Contact contactBuilder() {
        return Contact.builder()
                .contactId(1L)
                .contactType("email")
                .contactContent("contact@gmail.com").build();
    }

    public Contact contactNoIdBuilder() {
        return Contact.builder()
                .contactId(null)
                .contactType("email")
                .contactContent("contact@gmail.com").build();
    }
}
