package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AttendantBuilder {

    /*private Attendant attendantBuilder = AttendantBuilder.attendantBuilder(
            "1", "Mark", BigDecimal.valueOf(0));*/
    public static Attendant attendantBuilder() {
        return Attendant.builder().attendantId(1L)
                .name("Mikey")
                .dateOfBirth(LocalDate.of(1998, 12, 25))
                .driversLicenseNumber("1234567")
                .socialSecurityNumber("123456789")
                .totalCommission(BigDecimal.valueOf(0))
                .build();
    }

    public static Attendant attendantBuilderNoId() {
        return Attendant.builder().attendantId(null).name("Mikey")
                .dateOfBirth(LocalDate.of(1998, 12, 25))
                .driversLicenseNumber("1234567")
                .socialSecurityNumber("123456789")
                .totalCommission(BigDecimal.valueOf(0)).build();
    }
}
