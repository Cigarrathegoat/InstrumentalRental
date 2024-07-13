package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.models.AttendantBuilder;
import br.com.instrumental_rental.models.CustomerBuilder;
import br.com.instrumental_rental.models.InstrumentBuilder;
import br.com.instrumental_rental.models.RentalBuilder;
import br.com.instrumental_rental.repository.entities.Rental;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.impl.RentalService;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.util.ServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

   @Test
    void testSaveSuccess() throws CustomerNotFoundException, InstrumentNotFoundException,
           AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
           EndDateNotAfterStartDateException, StoreNotFoundException, RentalNotFoundException {
       var customer = CustomerBuilder.customerBuilder();
       var instrument = InstrumentBuilder.instrumentBuilder();
       var attendant = AttendantBuilder.attendantBuilder();
       var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
       var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);

       when(rentalRepository.save(rentalBuilderBeforeSave)).thenReturn(rentalBuilderAfterSave);
       Rental result = rentalService.save(rentalBuilderBeforeSave);
       Assertions.assertEquals(rentalBuilderAfterSave, result);
   }

    @Test
    void testSaveCustomerNotFoundException() throws CustomerNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        when(customerService.findCustomerById(
                rentalBuilderBeforeSave.getCustomer().getPersonId())).thenThrow(
                new CustomerNotFoundException("C01", "Customer not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class,
                () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                });
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void testSaveInstrumentNotFoundException() throws InstrumentNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        when(instrumentService.findById(
                rentalBuilderBeforeSave.getInstrument().getInstrumentId())).thenThrow(
                new InstrumentNotFoundException("I01", "Instrument not found"));
        InstrumentNotFoundException thrown = Assertions.assertThrows(InstrumentNotFoundException.class,
                () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                });
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void testSaveAttendantNotFoundException() throws AttendantNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        when(attendantService.findAttendantById(
                rentalBuilderBeforeSave.getAttendant().getPersonId())).thenThrow(
                new AttendantNotFoundException("A01", "Attendant not found"));
        AttendantNotFoundException thrown = Assertions.assertThrows(AttendantNotFoundException.class,
                () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                });
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }

    /*TODO ask him why this exception didn't light up*/
    @Test
    void testSaveWithdrawalGreaterThanBalanceException() throws WithdrawalGreaterThanBalanceException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        instrument.setPrice(BigDecimal.valueOf(100000));
        WithdrawalGreaterThanBalanceException thrown = Assertions.assertThrows(
                WithdrawalGreaterThanBalanceException.class, () -> {
                    rentalService.save(rentalBuilderBeforeSave);
                }
        );
        Assertions.assertEquals("W01", thrown.getCode());
        Assertions.assertEquals("Withdrawal greater than balance", thrown.getMessage());
    }

    /*TODO ask him why this exception didn't work either*/
    @Test
    void testSaveEndDateNotAfterStartDateException() throws EndDateNotAfterStartDateException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        rentalBuilderBeforeSave.setEndDate(
                LocalDate.parse("2020-10-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        EndDateNotAfterStartDateException thrown = Assertions.assertThrows(
                EndDateNotAfterStartDateException.class, () -> {rentalService.save(rentalBuilderBeforeSave);});
        Assertions.assertEquals("E01", thrown.getCode());
        Assertions.assertEquals("the end date must be at least one day after the start date",
                thrown.getMessage());
    }

    @Test
    void testSaveFirstTimeSuccess() throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, StoreNotFoundException, RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);

        when(rentalRepository.save(rentalBuilderBeforeSave)).thenReturn(rentalBuilderAfterSave);
        List<Rental> result = rentalService.saveFirstTime(List.of(rentalBuilderBeforeSave));
        verify(rentalRepository, times(1)).save(rentalBuilderBeforeSave);
        Assertions.assertEquals(List.of(rentalBuilderAfterSave), result);
    }
    @Test
    void testSaveFirstTimeCustomerNotFoundException() throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderBeforeSave = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        when(customerService.findCustomerById(rentalBuilderBeforeSave.getCustomer().getPersonId()))
                .thenThrow(new CustomerNotFoundException("C01", "Customer not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class, () ->
        {rentalService.save(rentalBuilderBeforeSave);});
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    /*TODO ask him why the assertions commented didn't work*/
    @Test
    void testDeleteSuccess() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);
        when(rentalRepository.findById(rentalBuilderAfterSave.getRentalId()))
                .thenReturn(Optional.of(rentalBuilderAfterSave));
        rentalService.delete(rentalBuilderAfterSave.getRentalId());
        /*Assertions.assertNull(rentalBuilderAfterSave);*/
        /*TODO never return a null*/
        verify(rentalRepository).findById(rentalBuilderAfterSave.getRentalId());
        verify(rentalRepository).delete(rentalBuilderAfterSave);
    }

    @Test
    void testDeleteRentalNotFoundException() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);
        when(rentalRepository.findById(rentalBuilderAfterSave.getRentalId()))
                .thenReturn(Optional.empty());
        RentalNotFoundException thrown = Assertions.assertThrows(RentalNotFoundException.class,
                () -> {rentalService.delete(rentalBuilderAfterSave.getRentalId());});
        Assertions.assertEquals("R01", thrown.getCode());
        Assertions.assertEquals("Rental not found", thrown.getMessage());
    }

    @Test
    void testFindAll() {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);
        when(rentalRepository.findAll()).thenReturn(List.of(rentalBuilderAfterSave));
        List<Rental> result = rentalService.findAll();
        Assertions.assertEquals(List.of(rentalBuilderAfterSave), result);
    }

    /*TODO ask him how this worked if the find method is looking for a word
       and the column at the table is an ID (WOULD WORK WITH A JOIN)*/
    @Test
    void testFindByWordSuccess() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);
        when(rentalRepository.findRentalByWord(rentalBuilderAfterSave.getCustomer().getName()))
                .thenReturn(List.of(rentalBuilderAfterSave));
        List<Rental> result = rentalService.findRentalListByWord(
                rentalBuilderAfterSave.getCustomer().getName());
        Assertions.assertEquals(List.of(rentalBuilderAfterSave), result);
    }
    /*TODO how does a test method check the real method if I tell it what to do*/

    @Test
    void testFindByWordRentalNotFoundException() throws RentalNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var rentalBuilderAfterSave = RentalBuilder.rentalBuilder(customer, instrument, attendant);
        when(rentalRepository.findRentalByWord(anyString())).thenReturn(List.of());
        RentalNotFoundException thrown = Assertions.assertThrows(RentalNotFoundException.class,
                () -> {rentalService.findRentalListByWord(anyString());});
        Assertions.assertEquals("R01", thrown.getCode());
        Assertions.assertEquals("no rentals found", thrown.getMessage());
    }
}
