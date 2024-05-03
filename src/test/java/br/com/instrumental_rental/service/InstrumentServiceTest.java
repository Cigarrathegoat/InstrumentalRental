package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.models.InstrumentBuilder;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.interfaces.IInstrumentRepository;
import br.com.instrumental_rental.service.impl.InstrumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstrumentServiceTest {

    @Mock
    IInstrumentRepository instrumentRepository;

    @Mock
    Logger log;

    @InjectMocks
    InstrumentService instrumentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSuccess() throws InstrumentNotFoundException, StoreNotFoundException{
        var builder = InstrumentBuilder.instrumentBuilder();
        var builderNoId = InstrumentBuilder.instrumentNoIdBuilder();
        when(instrumentRepository.save(builderNoId)).thenReturn(builder);
        Instrument saved = instrumentService.save(builderNoId);
        Assertions.assertEquals(builder, saved);
        //Assertions.assertEquals("31/12/2002", saved.getCreatedDate.toString)
        //Assertions.assertEquals(BigDecimal.valueOf(5000), experienceGained)
    }

    @Test
    void testSaveFirstTimeSuccess() {
        var builder = InstrumentBuilder.instrumentBuilder();
        var builderNoId = InstrumentBuilder.instrumentNoIdBuilder();
        when(instrumentRepository.save(builderNoId)).thenReturn(builder);
        List<Instrument> savedList = instrumentService.saveFirstTime(List.of(builderNoId));
        Assertions.assertEquals(List.of(builder), savedList);
    }

    @Test
    void testFindInstrumentByModelSuccess() throws InstrumentNotFoundException {
        var builder = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findInstrumentByMakeOrModel(builder.getModel()))
                .thenReturn(List.of(builder));
        List<Instrument> found = instrumentService.findInstrumentByMakeOrModel(builder.getModel());
        Assertions.assertIterableEquals(List.of(builder), found);
    }

    @Test
    void testFindInstrumentByModelInstrumentNotFoundException()
        throws InstrumentNotFoundException {
        var builder = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findInstrumentByMakeOrModel(builder.getModel()))
                .thenReturn(List.of());
        InstrumentNotFoundException thrown = Assertions.assertThrows(
                InstrumentNotFoundException.class, () -> {
                    instrumentService.findInstrumentByMakeOrModel(builder.getModel());
                }
        );
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void testListAll() {
        var builder = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findAll()).thenReturn(List.of(builder));
        List<Instrument> listed = instrumentService.listAll();
        Assertions.assertIterableEquals(List.of(builder), listed);
    }

    @

    @Test
    void testUpdateSuccess() throws InstrumentNotFoundException, StoreNotFoundException {
        var builder = InstrumentBuilder.instrumentBuilder();
        var builderUpdated = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findById(builder.getInstrumentId()))
                .thenReturn(Optional.of(builder));
        when(instrumentRepository.save(builderUpdated))
                .thenReturn(builderUpdated);
        Instrument updated = instrumentService.update(builder);
        Assertions.assertNotNull(updated);
    }

    @Test
    void testUpdateInstrumentNotFoundException() throws InstrumentNotFoundException {
        var builder = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findById(builder.getInstrumentId()))
                .thenReturn(Optional.empty());
        InstrumentNotFoundException thrown = Assertions.assertThrows(
                InstrumentNotFoundException.class, () -> {
                    instrumentService.update(builder);
                }
        );
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws InstrumentNotFoundException {
        var builder = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findById(builder.getInstrumentId()))
                .thenReturn(Optional.of(builder));
        instrumentService.delete(builder.getInstrumentId());
        verify(instrumentRepository).findById(builder.getInstrumentId());
        verify(instrumentRepository).delete(builder);
    }

    @Test
    void testDeleteInstrumentNotFoundException() throws InstrumentNotFoundException {
        var builder = InstrumentBuilder.instrumentBuilder();
        when(instrumentRepository.findById(builder.getInstrumentId()))
                .thenReturn(Optional.empty());
        InstrumentNotFoundException thrown = Assertions.assertThrows(
                InstrumentNotFoundException.class, () -> {
                    instrumentService.delete(builder.getInstrumentId());
                }
        );
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

}
