package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RentalBuilder {

    /*private LocalDate rentalStartDate = LocalDate.parse("2020-12-01",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private LocalDate rentalEndDate = LocalDate.parse("2020-12-03",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private BigDecimal rentalPrice = BigDecimal.valueOf(120);
    private BigDecimal attendantCommission = BigDecimal.valueOf(12);
    private Rental rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(
            customerBuilder, attendantBuilder, instrumentBuilder, rentalStartDate, rentalEndDate);*/
    public static Rental rentalBuilderBeforeSave(Customer customer, Instrument instrument,
                                                 Attendant attendant) {
        return Rental.builder().rentalId(null).customer(customer).instrument(instrument)
                .attendant(attendant).startDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).endDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).price(null).attendantCommission(null)
                .build();
    }

    /*TODO turn all id parameters into longs now*/
    public static Rental rentalBuilder(Customer customer, Instrument instrument,
                                                Attendant attendant) {


        return Rental.builder().rentalId(1L).customer(customer).attendant(attendant)
                .instrument(instrument).startDate(LocalDate.parse("2020-12-01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).endDate(LocalDate.parse("2020-12-03",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))).price(BigDecimal.valueOf(120))
                .attendantCommission(BigDecimal.valueOf(12))
                .build();
    }

}