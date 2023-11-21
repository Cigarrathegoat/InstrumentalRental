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

import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    ICustomerRepository customerRepository;

    @Mock
    Logger log;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveSuccess() {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        var builderNoId = CustomerBuilder.customerNoIdBuilder(
                "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        when(customerRepository.save(builderNoId)).thenReturn(builder);
        Customer saved = customerService.save(builderNoId);
        Assertions.assertNotNull(saved);
    }

    @Test
    void testFindCustomerByNumberProvidedSuccess() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        when(customerRepository.findCustomerByNumberProvided(builder.getDriversLicenseNumber()))
                .thenReturn(builder);
        Customer result = customerService.findCustomerByNumberProvided(
                builder.getDriversLicenseNumber());
        Assertions.assertEquals(builder, result);
    }

    @Test
    void testFindCustomerByNameCustomerNotFoundException() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        when(customerRepository.findCustomerByNumberProvided(builder.getSocialSecurityNumber()))
                .thenReturn(null);
        CustomerNotFoundException thrown = Assertions.assertThrows(
                CustomerNotFoundException.class, () -> {
                    customerService.findCustomerByNumberProvided(builder.getName());
                }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    @Test
    void testFindAllSuccess() {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        when(customerRepository.findAll()).thenReturn(List.of(builder));
        List<Customer> result = customerService.findAll();
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateSuccess() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500)
        );
        var builderUpdated = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-25"),
                "123456789", "1234567",
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
                "123456789", "1234567",
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
                "123456789", "1234567",BigDecimal.valueOf(500));
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.of(builder));
        customerService.delete(builder);
        verify(customerRepository, times(1)).findById(builder.getCustomerId());
        verify(customerRepository, times(1)).delete(builder);
    }

    @Test
    void testDeleteCustomerNotFoundException() throws CustomerNotFoundException {
        var builder = CustomerBuilder.customerBuilder(
                "1", "john", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
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

    /*public BigDecimal addToBalance(String customerId, BigDecimal addition)
            throws CustomerNotFoundException {
        var customerFound = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                                "C01", "Customer not found"
                        )
                );
        customerFound.setAccountBalance(customerFound.getAccountBalance().add(addition));
        return customerFound.getAccountBalance();
    }*/

    @Test
    void testAddToBalanceSuccess() throws CustomerNotFoundException {
        final BigDecimal addition = BigDecimal.valueOf(300);
        var builder = CustomerBuilder.customerBuilder(
                "01", "John", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.of(builder));
        BigDecimal newBalance = customerService.addToBalance(builder.getCustomerId(), addition);
        Assertions.assertEquals(BigDecimal.valueOf(800), newBalance);
    }

    @Test
    void testAddToBalanceCustomerNotFoundException() throws CustomerNotFoundException {
        final BigDecimal addition = BigDecimal.valueOf(300);
        var builder = CustomerBuilder.customerBuilder(
                "01", "John", LocalDate.parse("1992-08-23"),
                "123456789", "1234567",
                BigDecimal.valueOf(500));
        when(customerRepository.findById(builder.getCustomerId()))
                .thenReturn(Optional.empty());
        CustomerNotFoundException thrown = Assertions.assertThrows(
                CustomerNotFoundException.class, () -> {
                    customerService.addToBalance(builder.getCustomerId(), addition);
                }
        );
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }
}
