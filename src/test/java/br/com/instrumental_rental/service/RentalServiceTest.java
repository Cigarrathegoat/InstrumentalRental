package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.models.AttendantBuilder;
import br.com.instrumental_rental.models.CustomerBuilder;
import br.com.instrumental_rental.models.InstrumentBuilder;
import br.com.instrumental_rental.models.RentalBuilder;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.impl.RentalService;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;

public class RentalServiceTest {

    @Mock
    IRentalRepository rentalRepository;

    @Mock
    ICustomerService customerService;

    @Mock
    IInstrumentService instrumentService;

    @Mock
    IAttendantService attendantService;

    @InjectMocks
    RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Customer customerBuilder = CustomerBuilder.customerBuilder(
            "01", "John", LocalDate.parse("1985-08-23"),
            "123456789", "1234567",
            BigDecimal.valueOf(500));
    private Instrument instrumentBuilder = InstrumentBuilder.instrumentBuilder(
            "1", "microphone", "Yamaha", "Supermic 4000",
            BigDecimal.valueOf(4000), LocalDate.parse("2005-12-31",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    private Attendant attendantBuilder = AttendantBuilder.attendantBuilder(
            "1", "Mark", BigDecimal.valueOf(0));
    private LocalDate rentalStartDate = LocalDate.parse("2020-12-01",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private LocalDate rentalEndDate = LocalDate.parse("2020-12-03",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private BigDecimal rentalPrice = BigDecimal.valueOf(120);
    private BigDecimal attendantCommission = BigDecimal.valueOf(12);
    private Rental rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(
            customerBuilder, attendantBuilder, instrumentBuilder, rentalStartDate, rentalEndDate);
    private Rental rentalBuilderAfterSave = RentalBuilder.rentalBuilderAfterSave(
            "01", customerBuilder, attendantBuilder, instrumentBuilder, rentalStartDate,
            rentalEndDate, rentalPrice, attendantCommission);

    @Test
    void testSaveSuccess() throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException {
    when(rentalRepository.save(rentalBuilderBeforeSave)).thenReturn(rentalBuilderAfterSave);
    Rental result = rentalService.save(rentalBuilderBeforeSave);
        Assertions.assertEquals(rentalBuilderAfterSave, result);
    }



}
