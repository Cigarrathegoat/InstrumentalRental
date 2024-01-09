package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.models.AttendantBuilder;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.interfaces.IAttendantRepository;
import br.com.instrumental_rental.service.impl.AttendantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class AttendantServiceTest {

    @Mock
    IAttendantRepository attendantRepository;

    @InjectMocks
    AttendantService attendantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveSuccess() {
        var builder = AttendantBuilder.attendantBuilder();
        var builderNoId = AttendantBuilder.attendantBuilderNoId();
        when(attendantRepository.save(builderNoId)).thenReturn(builder);
        Attendant saved = attendantService.save(builderNoId);
        Assertions.assertNotNull(saved);
    }

    @Test
    void findAttendantByNumberProvidedSuccess() throws AttendantNotFoundException {
        var builder = AttendantBuilder.attendantBuilder();
        when(attendantRepository.findAttendantByNumberProvided(builder.getDriversLicenseNumber()))
                .thenReturn(builder);
       Attendant found = attendantService.findAttendantByNumberProvided(
                builder.getDriversLicenseNumber());
        Assertions.assertEquals(builder, found);
    }

    @Test
    void findAttendantByNumberProvidedAttendantNotFoundException() throws AttendantNotFoundException {
        when(attendantRepository.findAttendantByNumberProvided(anyString()))
                .thenReturn(null);
        AttendantNotFoundException thrown = Assertions.assertThrows(
                AttendantNotFoundException.class, () -> {
                    attendantService.findAttendantByNumberProvided(anyString());
                }
        );
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }

    @Test
    void updateAttendantSuccess() throws AttendantNotFoundException {
        var builder = AttendantBuilder.attendantBuilder();
        var builderUpdated = AttendantBuilder.attendantBuilder();
        when(attendantRepository.findById(builder.getAttendantId()))
                .thenReturn(Optional.of(builder));
        when(attendantRepository.save(builderUpdated)).thenReturn(builderUpdated);
        Attendant updated = attendantService.update(builder);
        Assertions.assertNotNull(updated);
    }

    @Test
    void updateAttendantNotFoundException() throws AttendantNotFoundException {
        var builder = AttendantBuilder.attendantBuilder();
        when(attendantRepository.findById(builder.getAttendantId()))
                .thenReturn(Optional.empty());
        AttendantNotFoundException thrown = Assertions.assertThrows(
                AttendantNotFoundException.class, () -> {
                    attendantService.update(builder);
                }
        );
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws AttendantNotFoundException {
        var builder = AttendantBuilder.attendantBuilder();
        when(attendantRepository.findById(builder.getAttendantId()))
                .thenReturn(Optional.of(builder));
        attendantService.delete(builder);
        verify(attendantRepository).findById(builder.getAttendantId());
        verify(attendantRepository).delete(builder);
    }

    @Test
    void testDeleteAttendantNotFoundException() throws AttendantNotFoundException {
        var builder = AttendantBuilder.attendantBuilder();
        when(attendantRepository.findById(builder.getAttendantId()))
                .thenReturn(Optional.empty());
        AttendantNotFoundException thrown = Assertions.assertThrows(
                AttendantNotFoundException.class, () -> {
                    attendantService.delete(builder);
                });
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("Attendant not found", thrown.getMessage());
    }
}
