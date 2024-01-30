package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressResponseDTO;
import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.repository.entities.TheAddress;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ITheAddressAPI {

    /* @ApiOperation(value = "add Customer object",
    response = CustomerDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code =404, response = ErrorSpecificationDTO.class, message = "not found")})
    CustomerResponseDTO add(CustomerDTO customerDTO);
     */

    @ApiOperation(value = "add TheAddress object",
    response = TheAddressResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    TheAddressResponseDTO add(TheAddressDTO theAddressDTO);

    @ApiOperation(value = "finds TheAddress object",
    response = TheAddressResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "not found")})
    TheAddressResponseDTO find(Long theAddressID) throws TheAddressNotFoundException;

    @ApiOperation(value = "update TheAddress object",
    response = TheAddressResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "not found")})
    TheAddressResponseDTO update(Long theAddressID) throws TheAddressNotFoundException;

    /*@ApiOperation(value = "delete Contacts object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ResponseEntity<DeleteResponseDTO> delete(Long contactsId) throws ContactNotFoundException;

     */

    @ApiOperation(value = "delete TheAddress object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "not found")})
    ResponseEntity<DeleteResponseDTO> delete(Long theAddressID) throws TheAddressNotFoundException;
}
