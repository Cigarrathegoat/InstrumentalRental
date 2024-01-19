package br.com.instrumental_rental.service;

import br.com.instrumental_rental.repository.interfaces.ITheAddressRepository;
import br.com.instrumental_rental.service.impl.TheAddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TheAddressServiceTest {

    @Mock
    ITheAddressRepository theAddressRepository;

    @InjectMocks
    TheAddressService addressService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSuccess() {

    }

}
