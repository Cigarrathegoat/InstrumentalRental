package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.ITheAddressMapper;
import br.com.instrumental_rental.controller.api.TheAddressAPI;
import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressResponseDTO;
import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.models.TheAddressBuilder;
import br.com.instrumental_rental.models.TheAddressDTOBuilder;
import br.com.instrumental_rental.repository.entities.TheAddress;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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
    void testSaveSuccess() {
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

    @Test
    void testFindSuccess() throws TheAddressNotFoundException {
        var builder = TheAddressBuilder.theAddressBuilder();
        var builderDTO = TheAddressDTOBuilder.theAddressDTOBuilder();
        when(theAddressService.findById(builder.getAddressId())).thenReturn(builder);
        when(theAddressMapper.convertToDto(builder)).thenReturn(builderDTO);
        TheAddressResponseDTO result = theAddressAPI.find(builder.getAddressId());
        Assertions.assertEquals(TheAddressResponseDTO.builder().data(builderDTO).build(), result);
    }

    @Test
    void testFindTheAddressNotFoundException() throws TheAddressNotFoundException {
        var builder = TheAddressBuilder.theAddressNoIdBuilder();
        when(theAddressService.findById(builder.getAddressId()))
                .thenThrow(new TheAddressNotFoundException("A01", "Address not found"));
        TheAddressNotFoundException thrown = Assertions.assertThrows(TheAddressNotFoundException.class,
                () -> {theAddressAPI.find(builder.getAddressId());});
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Address not found", thrown.getMessage());
    }

    @Test
    void testListAll() {
        var builder = TheAddressBuilder.theAddressNoIdBuilder();
        var builderDTO = TheAddressDTOBuilder.theAddressDTOBuilder();
        when(theAddressService.findAll()).thenReturn(List.of(builder));
        when(theAddressMapper.convertToListDTO(List.of(builder))).thenReturn(List.of(builderDTO));
        TheAddressListResponseDTO result = theAddressAPI.listAll();
        Assertions.assertEquals(
                TheAddressListResponseDTO.builder().data(List.of(builderDTO)).build(), result);
    }

    @Test
    void testUpdateSuccess() throws TheAddressNotFoundException {
        var builder = TheAddressBuilder.theAddressNoIdBuilder();
        var builderDTO = TheAddressDTOBuilder.theAddressDTOBuilder();
        when(theAddressService.findById(builder.getAddressId())).thenReturn(builder);
        when(theAddressService.update(builder)).thenReturn(builder);
        when(theAddressMapper.convertToDto(builder)).thenReturn(builderDTO);
        TheAddressResponseDTO result = theAddressAPI.update(builder.getAddressId(), builderDTO);
        Assertions.assertEquals(TheAddressResponseDTO.builder().data(builderDTO).build(), result);
    }
}
