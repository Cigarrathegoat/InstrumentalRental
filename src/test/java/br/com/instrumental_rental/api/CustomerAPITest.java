package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.ICustomerMapper;
import br.com.instrumental_rental.controller.api.CustomerAPI;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerResponseDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.models.CustomerBuilder;
import br.com.instrumental_rental.models.CustomerDTOBuilder;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import static org.mockito.Mockito.when;

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
    void saveSuccess() {
        var customerDTONoId = CustomerDTOBuilder.customerDTONoIdBuilder();
        var customerNoId = CustomerBuilder.customerNoIdBuilder();
        var customer = CustomerBuilder.customerBuilder();
        var customerDTO = CustomerDTOBuilder.customerDTOBuilder();
        when(customerMapper.convertToEntity(customerDTONoId)).thenReturn(customerNoId);
        when(customerService.save(customerNoId)).thenReturn(customer);
        when(customerMapper.convertToDto(customer)).thenReturn(customerDTO);
        CustomerResponseDTO result = customerAPI.add(customerDTONoId);
        Assertions.assertEquals(CustomerResponseDTO.builder()
                .data(customerDTO).build(), result);
    }

    @Test
    void findSuccess() throws CustomerNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var customerDTO = CustomerDTOBuilder.customerDTOBuilder();
        when(customerService.findCustomerByNumberProvided(customer.getSocialSecurityNumber())).thenReturn(customer);
        when(customerMapper.convertToDto(customer)).thenReturn(customerDTO);
        CustomerResponseDTO result = customerAPI.find(customer.getSocialSecurityNumber());
        Assertions.assertEquals(CustomerResponseDTO.builder().data(customerDTO).build(), result);
    }

    @Test
    void findCustomerNotFoundException() throws CustomerNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        when(customerService.findCustomerByNumberProvided(customer.getSocialSecurityNumber()))
                .thenThrow(new CustomerNotFoundException("A01", "not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class, () ->
        {
            customerAPI.find(customer.getSocialSecurityNumber());
        });
        Assertions.assertEquals("A01", thrown.getCode());
        Assertions.assertEquals("not found", thrown.getMessage());
    }
}
