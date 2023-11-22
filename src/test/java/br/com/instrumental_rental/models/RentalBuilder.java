package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalBuilder {

    public static Rental rentalBuilder(String id, Customer customer, Attendant attendant,
                                       Instrument instrument, LocalDate startDate,
                                       LocalDate endDate, BigDecimal price) {
        return Rental.builder().rentalId(id).customer(customer).attendant(attendant)
                .instrument(instrument).startDate(startDate).endDate(endDate).price(price)
                .build();
    }

    public static Rental rentalBuilderNoId(Customer customer, Attendant attendant,
                                           Instrument instrument, LocalDate startDate,
                                           LocalDate endDate, BigDecimal price) {
        return Rental.builder().customer(customer).attendant(attendant)
                .instrument(instrument).startDate(startDate).endDate(endDate).price(price)
                .build();
    }

}