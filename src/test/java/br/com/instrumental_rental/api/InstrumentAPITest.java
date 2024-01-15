package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.IInstrumentMapper;
import br.com.instrumental_rental.controller.api.InstrumentAPI;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    }
}
