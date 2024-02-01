package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.ITheAddressMapper;
import br.com.instrumental_rental.controller.ITheAddressAPI;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TheAddressAPITest {

    @Mock
    ITheAddressService theAddressService;

    @Mock
    ITheAddressMapper theAddressMapper;

    @InjectMocks
    ITheAddressAPI theAddressAPI;

    @BeforeEach
    void setUp()
}
