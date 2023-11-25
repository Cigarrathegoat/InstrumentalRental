package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class RentalBuilder {

    public static Rental rentalBuilderBeforeSave(Customer customer, Attendant attendant,
                                                 Instrument instrument, LocalDate startDate,
                                                 LocalDate endDate) {
        return Rental.builder().customer(customer).instrument(instrument)
                .startDate(startDate).endDate(endDate).attendant(attendant)
                .build();
    }

    /*TODO turn all id parameters into longs now*/
    public static Rental rentalBuilderAfterSave(String id, Customer customer, Attendant attendant,
                                           Instrument instrument, LocalDate startDate,
                                           LocalDate endDate, BigDecimal price,
                                                BigDecimal attendantCommission) {


        return Rental.builder().rentalId(id).customer(customer)
                .instrument(instrument).startDate(startDate).endDate(endDate).price(price)
                .attendant(attendant).attendantCommission(attendantCommission)
                .build();
    }

}