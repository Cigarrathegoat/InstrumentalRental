package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
}
