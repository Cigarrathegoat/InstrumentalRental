package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerBuilder {

    public static Customer customerBuilder(String id, String name, LocalDate date,
                                           String socialSecurityNumber, String driversLicenseNumber,
                                           BigDecimal balance) {
        return Customer.builder()
                .customerId(id).name(name).dateOfBirth(date)
                .socialSecurityNumber(socialSecurityNumber)
                .driversLicenseNumber(driversLicenseNumber)
                .accountBalance(balance).build();
    }

    public static Customer customerNoIdBuilder(String name, LocalDate date,
                                               String socialSecurityNumber, String driversLicenseNumber,
                                           BigDecimal balance) {
        return Customer.builder()
                .name(name).dateOfBirth(date)
                .socialSecurityNumber(socialSecurityNumber)
                .driversLicenseNumber(driversLicenseNumber)
                .accountBalance(balance).build();
    }
}
