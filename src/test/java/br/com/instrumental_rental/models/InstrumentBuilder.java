package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Instrument;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstrumentBuilder {

    public static Instrument instrumentBuilder(String id, String type, String make,
                                               String model, BigDecimal price, LocalDate date) {
        return Instrument.builder().instrumentId(id).type(type).make(make).model(model)
                .price(price).manufactureDate(date).available(true).build();
    }

    public static Instrument instrumentNoIdBuilder(String type, String make, String model,
                                               BigDecimal price, LocalDate date) {
        return Instrument.builder().instrumentId(null).type(type).make(make).model(model)
                .price(price).manufactureDate(date).available(true).build();
    }
}
