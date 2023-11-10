package br.com.instrumental_rental.service;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
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
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.Mockito.verify;
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

    @Test
    void testFindCustomerByNameSuccess() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500));
        when(customerRepository.findCustomerByName(builder.getName()))
                .thenReturn(List.of(builder));
        List<Customer> result = customerService.findCustomerByName(builder.getName());
        Assertions.assertNotNull(result);
    }

    @Test
    void testFindCustomerByNameCustomerNotFoundException() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500));
        when(customerRepository.findCustomerByName(builder.getName()))
                .thenReturn(List.of());
        CustomerNotFoundException thrown = Assertions.assertThrows(
                CustomerNotFoundException.class, () -> {
                    customerService.findCustomerByName(builder.getName());
                }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void testFindAllSuccess() {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500));
        when(customerRepository.findAll()).thenReturn(List.of(builder));
        List<Customer> result = customerService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateSuccess() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500)
        );
        var builderUpdated = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-25"),
                BigDecimal.valueOf(600)
        );
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.of(builder));
        when(customerRepository.save(builderUpdated)).thenReturn(builderUpdated);
        Customer result = customerService.update(builder);
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateCustomerNotFoundException() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500)
        );
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.empty());
        CustomerNotFoundException thrown = Assertions.assertThrows(
                CustomerNotFoundException.class, () -> {
                    customerService.update(builder);
                }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void testDeleteSuccess() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500));
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.of(builder));
        customerService.delete(builder);
        verify(customerRepository).findById(builder.getCustomerId());
        verify(customerRepository).delete(builder);
    }

    @Test
    void testDeleteCustomerNotFoundException() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                BigDecimal.valueOf(500)
        );
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.empty());
        CustomerNotFoundException thrown = Assertions.assertThrows(
                CustomerNotFoundException.class, () -> {
                    customerService.delete(builder);
                }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }
}
