package br.com.instrumental_rental.service;

import br.com.instrumental_rental.models.CustomerBuilder;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.interfaces.ICustomerRepository;
import br.com.instrumental_rental.service.impl.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @Mock
    ICustomerRepository customerRepository;

    @Mock
    Logger log;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testSaveSuccess() {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500));
        var builderNoId = CustomerBuilder.customerNoIdBuilder(
                "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500));
        when(customerRepository.save(builderNoId)).thenReturn(builder);
        Customer saved = customerService.save(builderNoId);
        Assertions.assertNotNull(saved);
    }
}
