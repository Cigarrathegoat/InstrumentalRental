package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.AccountBalanceDTO;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AccountBalanceResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface ICustomerAPI {

    @ApiOperation(value = "add Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code =404, response = ErrorSpecificationDTO.class, message = "not found")})
    CustomerResponseDTO add(CustomerDTO customerDTO)
        throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException;

    @ApiOperation(value = "find Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public CustomerResponseDTO find(String number)
        throws CustomerNotFoundException;

    @ApiOperation(value = "list all Customer objects",
    response = CustomerListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public CustomerListResponseDTO listAll();

    @ApiOperation(value = "update Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public CustomerResponseDTO update(Long customerId, CustomerDTO customerDTO) throws CustomerNotFoundException;

    @ApiOperation(value = "add to accountBalance attribute in Customer object",
    response = AccountBalanceResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public AccountBalanceResponseDTO addToBalance(Long customerId, AccountBalanceDTO accountBalanceDTO)
            throws CustomerNotFoundException;

    @ApiOperation(value = "withdraw value from account balance",
    response = AccountBalanceResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public AccountBalanceResponseDTO withdraw(Long customerId, AccountBalanceDTO accountBalanceDTO)
            throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException;

    @ApiOperation(value = "delete Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public ResponseEntity<DeleteResponseDTO> delete(Long customerId)
        throws CustomerNotFoundException;
}
