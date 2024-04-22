package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IInstrumentMapper;
import br.com.instrumental_rental.controller.api.InstrumentAPI;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentResponseDTO;
import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.models.InstrumentBuilder;
import br.com.instrumental_rental.models.InstrumentDTOBuilder;
import br.com.instrumental_rental.repository.interfaces.IInstrumentRepository;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class InstrumentAPITest {

    @Mock
    IInstrumentService instrumentService;

    @Mock
    IInstrumentMapper instrumentMapper;

    @Mock
    IInstrumentRepository instrumentRepository;

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
    void testAddListSuccess() {
        var instrumentDTONoId = InstrumentDTOBuilder.instrumentDTONoIdBuilder();
        var instrumentNoId = InstrumentBuilder.instrumentNoIdBuilder();
        var instrument = InstrumentBuilder.instrumentBuilder();
        var response = InstrumentListResponseDTO.builder()
                .listAddedSuccessfully("List added successfully").build();

        when(instrumentMapper.convertToEntityList(List.of(instrumentDTONoId)))
                .thenReturn(List.of(instrumentNoId));
        when(instrumentService.saveFirstTime(List.of(instrumentNoId)))
                .thenReturn(List.of(instrument));
        ResponseEntity<InstrumentListResponseDTO> result =
                instrumentAPI.saveList(List.of(instrumentDTONoId));
        verify(instrumentMapper, times(1))
                .convertToEntityList(List.of(instrumentDTONoId));
        verify(instrumentService, times(1))
                .saveFirstTime(List.of(instrumentNoId));
        Assertions.assertEquals(response, result.getBody());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

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
        {
            instrumentAPI.find(instrument.getModel());
        });
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void testListAll() {
        var instrument = InstrumentBuilder.instrumentBuilder();
        var instrumentDTO = InstrumentDTOBuilder.instrumentDTOBuilder();
        when(instrumentService.listAll()).thenReturn(List.of(instrument));
        when(instrumentMapper.convertToListDTO(List.of(instrument))).thenReturn(List.of(instrumentDTO));
        InstrumentListResponseDTO result = instrumentAPI.listAll();
        Assertions.assertEquals(InstrumentListResponseDTO.builder().data(List.of(instrumentDTO)).build(), result);
    }

    @Test
    void updateSuccess() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        var instrumentDTO = InstrumentDTOBuilder.instrumentDTOBuilder();
        when(instrumentMapper.convertToEntity(instrumentDTO)).thenReturn(instrument);
        when(instrumentService.update(instrument)).thenReturn(instrument);
        when(instrumentMapper.convertToDTO(instrument)).thenReturn(instrumentDTO);
        InstrumentResponseDTO result = instrumentAPI.update(instrument.getInstrumentId(), instrumentDTO);
        Assertions.assertEquals(InstrumentResponseDTO.builder().data(instrumentDTO).build(), result);
    }

    @Test
    void updateInstrumentNotFoundException() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        var instrumentDTO = InstrumentDTOBuilder.instrumentDTOBuilder();
        when(instrumentMapper.convertToEntity(instrumentDTO)).thenReturn(instrument);
        when(instrumentService.update(instrument)).thenThrow(
                new InstrumentNotFoundException("I01", "Instrument not found")
        );
        InstrumentNotFoundException thrown = Assertions.assertThrows(InstrumentNotFoundException.class, () ->
        {
            instrumentAPI.update(instrument.getInstrumentId(), instrumentDTO);
        });
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }

    @Test
    void deleteSuccess() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        doNothing().when(instrumentService).delete(instrument.getInstrumentId());
        instrumentAPI.delete(instrument.getInstrumentId());
        verify(instrumentService, times(1)).delete(instrument.getInstrumentId());
    }

    @Test
    void deleteInstrumentNotFoundException() throws InstrumentNotFoundException {
        var instrument = InstrumentBuilder.instrumentBuilder();
        doThrow(new InstrumentNotFoundException("I01", "Instrument not found"))
                .when(instrumentService).delete(instrument.getInstrumentId());
        InstrumentNotFoundException thrown = Assertions.assertThrows(InstrumentNotFoundException.class, () ->
                {
                    instrumentAPI.delete(instrument.getInstrumentId());
                }
        );
        Assertions.assertEquals("I01", thrown.getCode());
        Assertions.assertEquals("Instrument not found", thrown.getMessage());
    }
}
