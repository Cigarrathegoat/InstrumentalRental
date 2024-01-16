package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IRentalMapper;
import br.com.instrumental_rental.controller.api.RentalAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalResponseDTO;
import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.models.*;
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

import static org.mockito.Mockito.when;

public class RentalAPITest {

    @Mock
    ICustomerService customerService;

    @Mock
    IInstrumentService instrumentService;

    @Mock
    IAttendantService attendantService;

    @Mock
    IRentalService rentalService;

    @Mock
    IRentalMapper rentalMapper;

    @InjectMocks
    RentalAPI rentalAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSuccess() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        var rental = RentalBuilder.rentalBuilder(customer, instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getCustomerId(), instrument.getInstrumentId(), attendant.getAttendantId());
        var rentalDTO = RentalDTOBuilder.rentalDTOBuilder(customer.getCustomerId(),
                instrument.getInstrumentId(), attendant.getAttendantId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId)).thenReturn(rental);
        when(rentalMapper.convertToDTO(rental)).thenReturn(rentalDTO);
        RentalResponseDTO result = rentalAPI.add(rentalDTONoId);
        Assertions.assertEquals(RentalResponseDTO.builder().data(rentalDTO).build(), result);
    }

    @Test
    void saveCustomerNotFoundException() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getCustomerId(), instrument.getInstrumentId(), attendant.getAttendantId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId)).thenThrow(new CustomerNotFoundException("C01", "Customer not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class, () ->
        {rentalAPI.add(rentalDTONoId);});
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void saveAttendantNotFoundException() throws CustomerNotFoundException, AttendantNotFoundException, InstrumentNotFoundException,
            EndDateNotAfterStartDateException, WithdrawalGreaterThanBalanceException {
        var customer = CustomerBuilder.customerBuilder();
        var attendant = AttendantBuilder.attendantBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var rentalNoId = RentalBuilder.rentalBuilderBeforeSave(customer, instrument, attendant);
        var rentalDTONoId = RentalDTOBuilder.rentalDTOBuilderBeforeSave(
                customer.getCustomerId(), instrument.getInstrumentId(), attendant.getAttendantId());
        when(rentalMapper.convertToEntity(rentalDTONoId)).thenReturn(rentalNoId);
        when(rentalService.save(rentalNoId)).thenThrow(new AttendantNotFoundException("A01", "Attendant not found"));
        AttendantNotFoundException thrown = Assertions.assertThrows(AttendantNotFoundException.class, () ->
        {rentalAPI.add(rentalDTONoId);});
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }
}
