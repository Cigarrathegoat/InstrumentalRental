package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;

import java.time.LocalDate;

public class AttendantDTOBuilder {

    public static AttendantDTO attendantDTOSuccessBuilder() {
        return AttendantDTO.builder()
                .attendantId(1L)
                .name("Mikey")
                .birthDate(LocalDate.of(1998, 12, 25))
                .driversLicense("1234567")
                .socialSecurityNumber("123456789").build();
    }

    public static AttendantDTO attendantDTONoIdSuccessBuilder() {
        return AttendantDTO.builder()
                .name("Mikey")
                .birthDate(LocalDate.of(1998, 12, 25))
                .driversLicense("1234567")
                .socialSecurityNumber("123456789")
                .build();
    }
}
