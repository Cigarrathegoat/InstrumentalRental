package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.models.StoreBuilder;
import br.com.instrumental_rental.repository.entities.Store;
import br.com.instrumental_rental.repository.interfaces.IStoreRepository;
import br.com.instrumental_rental.service.impl.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class StoreServiceTest {

    @Mock
    IStoreRepository storeRepository;

    @InjectMocks
    StoreService storeService;

    @BeforeEach
    void setUp() {MockitoAnnotations.openMocks(this);}

    @Test
    void testSaveSuccess() throws StoreNotFoundException {
        var storeNoId = StoreBuilder.storeNoIdBuilder();
        var store = StoreBuilder.storeBuilder();

        when(storeRepository.save(storeNoId)).thenReturn(store);
        Store result = storeService.save(storeNoId);
        verify(storeRepository, times(1)).save(storeNoId);
        Assertions.assertEquals(store, result);
    }
}
