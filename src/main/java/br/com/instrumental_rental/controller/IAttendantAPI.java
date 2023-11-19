package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantResponseDTO;
import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IAttendantAPI {

    @ApiOperation(value = "add Attendant object",
    response = AttendantDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    AttendantResponseDTO add(AttendantDTO attendantDTO)
        throws CustomerNotFoundException;

    @ApiOperation(value = "find Attendant object",
    response = AttendantListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public AttendantListResponseDTO find(String attendantName)
        throws AttendantNotFoundException;

    @ApiOperation(value = "find all Customer objects",
    response = AttendantListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public AttendantListResponseDTO findAll();
}
