package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.ICustomerMapper;
import br.com.instrumental_rental.controller.api.CustomerAPI;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

public class CustomerAPITest {

    @Mock
    ICustomerService customerService;

    @Mock
    ICustomerMapper customerMapper;

    @InjectMocks
    CustomerAPI customerAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    private void saveSuccess() {

    }
}
