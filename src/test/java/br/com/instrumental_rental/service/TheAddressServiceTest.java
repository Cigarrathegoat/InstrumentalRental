package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.models.TheAddressBuilder;
import br.com.instrumental_rental.repository.entities.TheAddress;
import br.com.instrumental_rental.repository.interfaces.ITheAddressRepository;
import br.com.instrumental_rental.service.impl.TheAddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class TheAddressServiceTest {

    @Mock
    ITheAddressRepository theAddressRepository;

    @InjectMocks
    TheAddressService theAddressService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveSuccess() {
        var builderNoId = TheAddressBuilder.theAddressNoIdBuilder();
        var builder = TheAddressBuilder.theAddressBuilder();
        when(theAddressRepository.save(builderNoId)).thenReturn(builder);
        TheAddress result = theAddressService.save(builderNoId);
        Assertions.assertEquals(builder, result);
    }

    @Test
    void findByIdSuccess() throws TheAddressNotFoundException {
        var builder = TheAddressBuilder.theAddressBuilder();
        when(theAddressRepository.findById(builder.getAddressId()))
                .thenReturn(Optional.of(builder));
        TheAddress result = theAddressService.findById(builder.getAddressId());
        Assertions.assertEquals(builder, result);
    }

}
