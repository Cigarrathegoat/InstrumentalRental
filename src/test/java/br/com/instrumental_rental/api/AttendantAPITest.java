package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IAttendantMapper;
import br.com.instrumental_rental.controller.api.AttendantAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantResponseDTO;
import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.models.AttendantBuilder;
import br.com.instrumental_rental.models.AttendantDTOBuilder;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

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

    @Test
    void testFindAttendantNotFoundExceptionError() throws AttendantNotFoundException {
        var attendantSought = AttendantBuilder.attendantBuilder();
        when(attendantService.findAttendantByNumberProvided(attendantSought.getSocialSecurityNumber()))
                .thenThrow(new AttendantNotFoundException("A01", "Attendant not found"));
        AttendantNotFoundException thrown = Assertions.assertThrows(
                AttendantNotFoundException.class, () -> {
                    attendantAPI.find(attendantSought.getSocialSecurityNumber());
                }
        );
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }
    @Test
    void testListAllSuccess() {
        var attendantSought = AttendantBuilder.attendantBuilder();
        var attendantSoughtDTO = AttendantDTOBuilder.attendantDTOSuccessBuilder();
        when(attendantService.findAll()).thenReturn(List.of(attendantSought));
        when(attendantMapper.convertToListDto(List.of(attendantSought))).thenReturn(List.of(attendantSoughtDTO));
        AttendantListResponseDTO result = attendantAPI.listAll();
        Assertions.assertEquals(AttendantListResponseDTO.builder()
                .data(List.of(attendantSoughtDTO)).build(), result);
    }

    @Test
    void testUpdateSuccess() throws AttendantNotFoundException {
        var attendantSought = AttendantBuilder.attendantBuilder();
        var attendantSoughtDTO = AttendantDTOBuilder.attendantDTOSuccessBuilder();
        when(attendantMapper.convertToEntity(attendantSoughtDTO)).thenReturn(attendantSought);
        when(attendantService.update(attendantSought)).thenReturn(attendantSought);
        when(attendantMapper.convertToDTO(attendantSought)).thenReturn(attendantSoughtDTO);
        AttendantResponseDTO result = attendantAPI.update(attendantSought.getPersonId(), attendantSoughtDTO);
        Assertions.assertEquals(AttendantResponseDTO.builder().data(attendantSoughtDTO).build(), result);
    }

    @Test
    void testUpdateAttendantNotFoundException() throws AttendantNotFoundException {
        var attendantSought = AttendantBuilder.attendantBuilder();
        var attendantSoughtDTO = AttendantDTOBuilder.attendantDTOSuccessBuilder();
        when(attendantMapper.convertToEntity(attendantSoughtDTO)).thenReturn(attendantSought);
        when(attendantService.update(attendantSought))
                .thenThrow(new AttendantNotFoundException("A01", "Attendant not found"));
        AttendantNotFoundException thrown = Assertions.assertThrows(AttendantNotFoundException.class, () ->
        {attendantAPI.update(attendantSought.getPersonId(), attendantSoughtDTO);}
        );
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws AttendantNotFoundException {
        var attendantSought = AttendantBuilder.attendantBuilder();
        doNothing().when(attendantService).delete(attendantSought.getPersonId());
        assertDoesNotThrow(() -> attendantAPI.delete(attendantSought.getPersonId()));
    }

    @Test
    void testDeleteAttendantNotFoundException() throws AttendantNotFoundException {
        var attendantSought = AttendantBuilder.attendantBuilder();
        doThrow(new AttendantNotFoundException("A01", "Attendant not found")).when(attendantService)
                .delete(attendantSought.getPersonId());
        AttendantNotFoundException thrown = Assertions.assertThrows(AttendantNotFoundException.class,
                () -> {attendantAPI.delete(attendantSought.getPersonId());});
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }
}
