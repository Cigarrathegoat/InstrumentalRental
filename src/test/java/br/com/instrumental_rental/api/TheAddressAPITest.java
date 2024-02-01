package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.ITheAddressMapper;
import br.com.instrumental_rental.controller.api.TheAddressAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressResponseDTO;
import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.models.TheAddressBuilder;
import br.com.instrumental_rental.models.TheAddressDTOBuilder;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class TheAddressAPITest {

    @Mock
    ITheAddressService theAddressService;

    @Mock
    ITheAddressMapper theAddressMapper;

    @InjectMocks
    TheAddressAPI theAddressAPI;

    @BeforeEach
    void setUp(){MockitoAnnotations.openMocks(this);}

    @Test
    void testSaveSuccess() throws TheAddressNotFoundException {
        var builderNoId = TheAddressBuilder.theAddressNoIdBuilder();
        var builder = TheAddressBuilder.theAddressBuilder();
        var builderNoIdDTO = TheAddressDTOBuilder.theAddressDTONoIdBuilder();
        var builderDTO = TheAddressDTOBuilder.theAddressDTOBuilder();
        when(theAddressMapper.convertToEntity(builderNoIdDTO)).thenReturn(builderNoId);
        when(theAddressService.save(builderNoId)).thenReturn(builder);
        when(theAddressMapper.convertToDto(builder)).thenReturn(builderDTO);
        TheAddressResponseDTO result = theAddressAPI.add(builderNoIdDTO);
        Assertions.assertEquals(TheAddressResponseDTO.builder().data(builderDTO).build(), result);
    }
}
