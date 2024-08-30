package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.RentalDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalDTOBuilder {

    public static RentalDTO rentalDTOBuilderBeforeSave(Long customerId, Long instrumentId,
                                                    Long attendantId) {
        return RentalDTO.builder().rentalId(null).customerId(customerId).instrumentId(instrumentId)
                .attendantId(attendantId).startDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).endDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).price(null)
                .build();
    }

    public static RentalDTO rentalDTOBuilder(Long customerId, Long instrumentId,
                                                       Long attendantId) {
        return RentalDTO.builder().rentalId(1L).customerId(customerId).instrumentId(instrumentId)
                .attendantId(attendantId).startDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).endDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).price(null)
                .build();
    }
}
