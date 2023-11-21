package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.AccountBalanceDTO;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.CustomerResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ICustomerAPI {

    @ApiOperation(value = "add Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code =404, response = ErrorSpecificationDTO.class, message = "not found")})
    CustomerResponseDTO add(CustomerDTO customerDTO)
        throws CustomerNotFoundException;

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
    public CustomerResponseDTO update() throws CustomerNotFoundException;

    @ApiOperation(value = "add to accountBalance attribute in Customer object",
    response = AccountBalanceDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public AccountBalanceDTO addToBalance() throws CustomerNotFoundException;

    @ApiOperation(value = "delete Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public ResponseEntity<DeleteResponseDTO> delete(Long customerId)
        throws CustomerNotFoundException;
}
