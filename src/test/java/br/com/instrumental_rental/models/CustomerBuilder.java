package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerBuilder {

    public static Customer customerBuilder(String id, String name, LocalDate date,
                                  BigDecimal balance) {
        return Customer.builder()
                .customerId(id).name(name).dateOfBirth(date)
                .accountBalance(balance).build();
    }

    public static Customer customerNoIdBuilder(String name, LocalDate date,
                                           BigDecimal balance) {
        return Customer.builder()
                .customerId(null).name(name).dateOfBirth(date)
                .accountBalance(balance).build();
    }
}
