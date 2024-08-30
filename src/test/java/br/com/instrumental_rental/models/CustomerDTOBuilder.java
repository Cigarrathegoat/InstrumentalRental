package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerDTOBuilder {

    public static CustomerDTO customerDTOBuilder() {
        return CustomerDTO.builder()
                .customerId(1L)
                .name("Johnny")
                .dateOfBirth(LocalDate.parse("25-08-1985",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .address(null)
                .contacts(null)
                .build();
    }

    public static CustomerDTO customerDTONoIdBuilder() {
        return CustomerDTO.builder()
                .customerId(null)
                .name("Johnny")
                .dateOfBirth(LocalDate.parse("25-08-1985",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .address(null)
                .contacts(null)
                .build();
    }
}
