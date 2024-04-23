package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressResponseDTO;
import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ITheAddressAPI {

    @ApiOperation(value = "add TheAddress object",
    response = TheAddressResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    TheAddressResponseDTO add(TheAddressDTO theAddressDTO);

    @ApiOperation(value = "add TheAddress list")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    ResponseEntity<TheAddressListResponseDTO> addList(TheAddressDTO theAddressDTO);

    @ApiOperation(value = "finds TheAddress object",
    response = TheAddressResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "not found")})
    TheAddressResponseDTO find(Long theAddressID) throws TheAddressNotFoundException;

    @ApiOperation(value = "lists all TheAddress objects",
    response = TheAddressListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    TheAddressListResponseDTO listAll();

    @ApiOperation(value = "update TheAddress object",
    response = TheAddressResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "not found")})
    TheAddressResponseDTO update(Long theAddressID, TheAddressDTO theAddressDTO)
            throws TheAddressNotFoundException;

    @ApiOperation(value = "delete TheAddress object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "not found")})
    ResponseEntity<DeleteResponseDTO> delete(Long theAddressID) throws TheAddressNotFoundException;
}
