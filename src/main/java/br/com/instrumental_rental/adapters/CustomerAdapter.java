package br.com.instrumental_rental.adapters;

import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.repository.entities.Customer;

public class CustomerAdapter {

    public static Customer convertToEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .address(customerDTO.getAddress())
                .dateOfBirth(customerDTO.getDateOfBirth())
                .accountBalance(customerDTO.getAccountBalance())
                .contacts(customerDTO.getContacts())
                .build();
    }
}
