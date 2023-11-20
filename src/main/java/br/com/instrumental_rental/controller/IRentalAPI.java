package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.RentalDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.RentalResponseDTO;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IRentalAPI {

    @ApiOperation(value = "add Rental object",
    response = RentalResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public RentalResponseDTO add(RentalDTO rentalDTO)
        throws RentalNotFoundException;

    @ApiOperation(value = "find Rental object",
    response = RentalResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public RentalResponseDTO find(String rentalId) throws RentalNotFoundException;

    @ApiOperation(value = "find all Rental objects",
    response = RentalResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public RentalListResponseDTO findAll();

    @ApiOperation(value = "update Rental objects",
    response = RentalResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public RentalResponseDTO update(String rentalId);

    @ApiOperation(value = "delete Rental object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public ResponseEntity<DeleteResponseDTO> delete(String rentalId)
        throws RentalNotFoundException;
}
