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
import br.com.instrumental_rental.service.util.ServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.anyString;
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

    @Mock
    ServiceUtil serviceUtil;

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

    @Test
    void testSaveCustomerNotFoundException() throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException {
        when(customerService.findCustomerByNumberProvided(
                rentalBuilderBeforeSave.getCustomer().getSocialSecurityNumber())).thenThrow(
                new CustomerNotFoundException("C01", "Customer not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class,
                () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                });
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void testSaveInstrumentNotFoundException() throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException {
        when(instrumentService.findInstrumentByMakeOrModel(
                rentalBuilderBeforeSave.getInstrument().getModel())).thenThrow(
                new InstrumentNotFoundException("I01", "Instrument not found"));
        InstrumentNotFoundException thrown = Assertions.assertThrows(InstrumentNotFoundException.class,
                () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                });
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void testSaveAttendantNotFoundException() throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException {
        when(attendantService.findAttendantByNumberProvided(
                rentalBuilderBeforeSave.getAttendant().getSocialSecurityNumber())).thenThrow(
                new AttendantNotFoundException("A01", "Attendant not found"));
        AttendantNotFoundException thrown = Assertions.assertThrows(AttendantNotFoundException.class,
                () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                });
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }

    @Test
    void testSaveWithdrawalGreaterThanBalanceException() throws CustomerNotFoundException,
            InstrumentNotFoundException,  AttendantNotFoundException,
            WithdrawalGreaterThanBalanceException, EndDateNotAfterStartDateException {
        instrumentBuilder.setPrice(BigDecimal.valueOf(100000));
        WithdrawalGreaterThanBalanceException thrown = Assertions.assertThrows(
                WithdrawalGreaterThanBalanceException.class, () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                }
        );
        Assertions.assertEquals("W01", thrown.getCode());
        Assertions.assertEquals("Withdrawal greater than balance", thrown.getMessage());
    }

    @Test
    void testSaveEndDateNotAfterStartDateException() throws CustomerNotFoundException,
            InstrumentNotFoundException,  AttendantNotFoundException,
            WithdrawalGreaterThanBalanceException, EndDateNotAfterStartDateException {
        rentalEndDate = LocalDate.parse("2020-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }
}
