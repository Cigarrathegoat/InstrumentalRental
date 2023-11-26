package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Instrument;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InstrumentBuilder {

    public static Instrument instrumentBuilder() {
        return Instrument.builder().instrumentId(1L).type("Microphone").make("Yamaha").model("Supermic 4000")
                .price(BigDecimal.valueOf(4000)).manufactureDate(LocalDate.parse("2005-12-31",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).available(true).build();
    }

    public static Instrument instrumentNoIdBuilder(String type, String make, String model,
                                               BigDecimal price, LocalDate date) {
        return Instrument.builder().instrumentId(null).type("Microphone").make("Yamaha").model("Supermic 4000")
                .price(BigDecimal.valueOf(4000)).manufactureDate(LocalDate.parse("2005-12-31",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).available(true).build();
    }
}
