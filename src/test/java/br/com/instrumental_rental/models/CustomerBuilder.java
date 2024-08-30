package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerBuilder {

    /*Customer builder = CustomerBuilder.customerBuilder(
            "01", "John", LocalDate.parse("1992-08-23"),
            "123456789", "1234567",
            BigDecimal.valueOf(500));*/

    public static Customer customerBuilder() {
        return Customer.builder()
                .personId(1L).name("Johnny").dateOfBirth(LocalDate.parse("25-08-1985",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .socialSecurityNumber("123456789").driversLicenseNumber("1234567")
                .accountBalance(BigDecimal.valueOf(5000))
                .build();
    }

    public static Customer customerUpdatedBuilder() {
        return Customer.builder()
                .personId(1L).name("Johnny").dateOfBirth(LocalDate.parse("25-08-1985",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .socialSecurityNumber("123456780")
                .driversLicenseNumber("1234560")
                .accountBalance(BigDecimal.valueOf(500))
                .build();
    }

    public static Customer builderAfterWithdrawal() {
        return Customer.builder()
                .personId(1L).name("Johnny").dateOfBirth(LocalDate.parse("25-08-1985",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .socialSecurityNumber("123456780")
                .driversLicenseNumber("1234560")
                .accountBalance(BigDecimal.valueOf(0))
                .build();
    }

    public static Customer customerNoIdBuilder() {
        return Customer.builder()
                .name("John").dateOfBirth(LocalDate.parse("1985-08-23",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .socialSecurityNumber("123456789")
                .driversLicenseNumber("1234567")
                .accountBalance(BigDecimal.valueOf(500))
                .build();
    }
}
