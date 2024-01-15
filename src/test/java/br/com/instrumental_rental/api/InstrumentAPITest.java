package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IInstrumentMapper;
import br.com.instrumental_rental.controller.api.InstrumentAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentResponseDTO;
import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.models.InstrumentBuilder;
import br.com.instrumental_rental.models.InstrumentDTOBuilder;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

public class InstrumentAPITest {

    @Mock
    IInstrumentService instrumentService;

    @Mock
    IInstrumentMapper instrumentMapper;

    @InjectMocks
    InstrumentAPI instrumentAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSuccess() {
        var instrument = InstrumentBuilder.instrumentBuilder();
        var instrumentNoId = InstrumentBuilder.instrumentNoIdBuilder();
        var instrumentDTO = InstrumentDTOBuilder.instrumentDTOBuilder();
        var instrumentDTONoId = InstrumentDTOBuilder.instrumentDTONoIdBuilder();
        when(instrumentMapper.convertToEntity(instrumentDTONoId)).thenReturn(instrumentNoId);
        when(instrumentService.save(instrumentNoId)).thenReturn(instrument);
        when(instrumentMapper.convertToDTO(instrument)).thenReturn(instrumentDTO);
        InstrumentResponseDTO result = instrumentAPI.add(instrumentDTONoId);
        Assertions.assertEquals(InstrumentResponseDTO.builder().data(instrumentDTO).build(), result);
    }

    @Test
    void testFindSuccess() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        var instrumentDTO = InstrumentDTOBuilder.instrumentDTOBuilder();
        when(instrumentService.findInstrumentByMakeOrModel(instrument.getModel()))
                .thenReturn(List.of(instrument));
        when(instrumentMapper.convertToListDTO(List.of(instrument))).thenReturn(List.of(instrumentDTO));
        InstrumentListResponseDTO result = instrumentAPI.find(instrument.getModel());
        Assertions.assertEquals(InstrumentListResponseDTO.builder().data(List.of(instrumentDTO)).build(), result);
    }

    @Test
    void testFindInstrumentNotFoundException() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        when(instrumentService.findInstrumentByMakeOrModel(instrument.getModel()))
                .thenThrow(new InstrumentNotFoundException("I01", "Instrument not found"));
        InstrumentNotFoundException thrown = Assertions.assertThrows(InstrumentNotFoundException.class, () ->
        {instrumentAPI.find(instrument.getModel());});
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }
}
