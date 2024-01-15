package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.InstrumentDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InstrumentDTOBuilder {

    public static InstrumentDTO instrumentDTOBuilder() {
        return InstrumentDTO.builder().instrumentId(1L).type("Microphone").make("Yamaha").model("Supermic 4000")
                .price(BigDecimal.valueOf(4000)).manufactureDate(LocalDate.parse("2005-12-31",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).build();
    }

    public static InstrumentDTO instrumentDTONoIdBuilder() {
        return InstrumentDTO.builder().instrumentId(null).type("Microphone").make("Yamaha").model("Supermic 4000")
                .price(BigDecimal.valueOf(4000)).manufactureDate(LocalDate.parse("2005-12-31",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).build();
    }
}
