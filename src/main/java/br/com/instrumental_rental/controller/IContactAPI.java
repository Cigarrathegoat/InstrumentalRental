package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.ContactsListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.ContactsResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.ContactNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IContactAPI {

    /* @ApiOperation(value = "add Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code =404, response = ErrorSpecificationDTO.class, message = "not found")})
    CustomerResponseDTO add(CustomerDTO customerDTO);
     */

    @ApiOperation(value = "add Contacts object",
    response = ContactsResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ContactsResponseDTO add(ContactsDTO contactsDTO);

    @ApiOperation(value = "find API object",
    response = ContactsListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ContactsListResponseDTO find(String holder) throws ContactNotFoundException;

    @ApiOperation(value = "update Contacts object",
    response = ContactsResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ContactsResponseDTO update(Long contactsId) throws ContactNotFoundException;

    @ApiOperation(value = "delete Contacts object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ResponseEntity<DeleteResponseDTO> delete(Long contactsId) throws ContactNotFoundException;
}
