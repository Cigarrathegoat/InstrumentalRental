package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IRentalMapper;
import br.com.instrumental_rental.controller.api.RentalAPI;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}
