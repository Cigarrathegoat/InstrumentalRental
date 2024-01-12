package br.com.instrumental_rental.api;

import br.com.instrumental_rental.Mappers.ICustomerMapper;
import br.com.instrumental_rental.controller.api.CustomerAPI;
import br.com.instrumental_rental.controller.dto.requests.AccountBalanceDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AccountBalanceResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerResponseDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.models.CustomerBuilder;
import br.com.instrumental_rental.models.CustomerDTOBuilder;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

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

    @Test
    void listAllSuccess() {
        var customer = CustomerBuilder.customerBuilder();
        var customerDTO = CustomerDTOBuilder.customerDTOBuilder();
        when(customerService.findAll()).thenReturn(List.of(customer));
        when(customerMapper.convertoToListDto(List.of(customer))).thenReturn(List.of(customerDTO));
        CustomerListResponseDTO result = customerAPI.listAll();
        Assertions.assertEquals(CustomerListResponseDTO.builder().data(List.of(customerDTO)).build(), result);
    }

    @Test
    void updateSuccess() throws CustomerNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var customerDTO = CustomerDTOBuilder.customerDTOBuilder();
        when(customerMapper.convertToEntity(customerDTO)).thenReturn(customer);
        when(customerService.update(customer)).thenReturn(customer);
        when(customerMapper.convertToDto(customer)).thenReturn(customerDTO);
        CustomerResponseDTO result = customerAPI.update(customer.getCustomerId(), customerDTO);
        Assertions.assertEquals(CustomerResponseDTO.builder().data(customerDTO).build(), result);
    }

    @Test
    void updateCustomerNotFoundException() throws CustomerNotFoundException {
        var customer = CustomerBuilder.customerBuilder();
        var customerDTO = CustomerDTOBuilder.customerDTOBuilder();
        when(customerMapper.convertToEntity(customerDTO)).thenReturn(customer);
        when(customerService.update(customer)).thenThrow(new CustomerNotFoundException("C01", "Customer not found"));
        CustomerNotFoundException thrown = Assertions.assertThrows(CustomerNotFoundException.class, () ->
        {customerAPI.update(customer.getCustomerId(), customerDTO);});
        Assertions.assertEquals("C01", thrown.getCode());
        Assertions.assertEquals("Customer not found", thrown.getMessage());
    }

    /*
     @PutMapping("/add-to-balance/{customerId}")
    public AccountBalanceResponseDTO addToBalance(@PathVariable("customerId") Long customerId,
                                                  @RequestBody AccountBalanceDTO accountBalanceDTO)
            throws CustomerNotFoundException {
        return AccountBalanceResponseDTO.builder()
                .data(
                        customerMapperAttribute.convertAccountBalanceToDTO(
                                customerServiceAttribute.addToBalance(customerId,
                                BigDecimal.valueOf(accountBalanceDTO.getValueToAddOrWithdraw())))
                ).build();
    }
     */
    @Test
    void addToBalanceSuccess() throws CustomerNotFoundException {
        var value = BigDecimal.valueOf(600);
        var customer = CustomerBuilder.customerBuilder();
        var customerDTO = CustomerDTOBuilder.customerDTOBuilder();
        AccountBalanceDTO accountBalanceDTOBuilder = new AccountBalanceDTO();
        when(customerService.addToBalance(customer.getCustomerId(), value))
                .thenReturn(customer.getAccountBalance().add(value));
        AccountBalanceResponseDTO result = customerAPI.addToBalance(customer.getCustomerId(),
                accountBalanceDTOBuilder);
        Assertions.assertEquals(AccountBalanceResponseDTO.builder().data(accountBalanceDTOBuilder).build(), result);
    }
}
