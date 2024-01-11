package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;

public class CustomerDTOBuilder {

    public static CustomerDTO customerDTOBuilder() {
        return CustomerDTO.builder()
                .customerId(1L).name("Jamie").addresses(null).contacts(null).build();
    }

    public static CustomerDTO customerDTONoIdBuilder() {
        return CustomerDTO.builder()
                .customerId(null).name("Jamie").addresses(null).contacts(null).build();
    }
}
