package br.com.instrumental_rental.service;

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

import static org.mockito.Mockito.when;

public class AttendantServiceTest {

    @Mock
    IAttendantRepository attendantRepository;

    @InjectMocks
    AttendantService attendantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @Test
    void testSaveSuccess() {
        var builderNoId = AttendantBuilder.attendantBuilderNoId("Mark");
        var builder = AttendantBuilder.attendantBuilder("1", "Mark");
        when(attendantRepository.save(builderNoId)).thenReturn(builder);
        Attendant saved = attendantService.save(builderNoId);
        Assertions.assertNotNull(saved);
    }
}
