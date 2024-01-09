package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IAttendantMapper;
import br.com.instrumental_rental.controller.api.AttendantAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantResponseDTO;
import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.models.AttendantBuilder;
import br.com.instrumental_rental.models.AttendantDTOBuilder;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class AttendantAPITest {

    @Mock
    IAttendantService attendantService;

    @Mock
    IAttendantMapper attendantMapper;

    @InjectMocks
    AttendantAPI attendantAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @Test
    void testAddSuccess() {
        var attendantNoId = AttendantBuilder.attendantBuilderNoId();
        var attendantBuilder = AttendantBuilder.attendantBuilder();
        var attendantDTONoIdBuilder = AttendantDTOBuilder.attendantDTONoIdSuccessBuilder();
        var attendantDTOBuilder = AttendantDTOBuilder.attendantDTOSuccessBuilder();
        when(attendantMapper.convertToEntity(attendantDTONoIdBuilder)).thenReturn(attendantNoId);
        when(attendantService.save(attendantNoId)).thenReturn(attendantBuilder);
        when(attendantMapper.convertToDTO(attendantBuilder)).thenReturn(attendantDTOBuilder);
        AttendantResponseDTO result = attendantAPI.add(attendantDTONoIdBuilder);
        Assertions.assertEquals(AttendantResponseDTO.builder().data(attendantDTOBuilder).build(), result);
    }

    @Test
    void testFindSuccess() throws AttendantNotFoundException {
        var attendantSought = AttendantBuilder.attendantBuilder();
        var attendantSoughtDTO = AttendantDTOBuilder.attendantDTOSuccessBuilder();
        when(attendantService.findAttendantByNumberProvided(attendantSought.getSocialSecurityNumber()))
                .thenReturn(attendantSought);
        when(attendantMapper.convertToDTO(attendantSought)).thenReturn(attendantSoughtDTO);
        AttendantResponseDTO result = attendantAPI.find(attendantSought.getSocialSecurityNumber());
        Assertions.assertEquals(AttendantResponseDTO.builder().data(attendantSoughtDTO).build(), result);
    }


}
