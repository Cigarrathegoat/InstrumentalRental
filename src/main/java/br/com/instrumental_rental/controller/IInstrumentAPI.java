package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.InstrumentDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentResponseDTO;
import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IInstrumentAPI {

    @ApiOperation(value = "Add Instrument object",
    response = InstrumentResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public InstrumentResponseDTO add(InstrumentDTO instrumentDTO);

    @ApiOperation(value = "find Instrument object",
    response = InstrumentResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public InstrumentListResponseDTO find(String instrumentName)
        throws InstrumentNotFoundException;

    @ApiOperation(value = "find all Instrument objects",
    response = InstrumentResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public InstrumentListResponseDTO listAll();

    @ApiOperation(value = "update Instrument object",
    response = InstrumentResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public InstrumentResponseDTO update(String instrumentID, InstrumentDTO instrumentDTO)
        throws InstrumentNotFoundException;

    @ApiOperation(value = "delete Instrument object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public ResponseEntity<DeleteResponseDTO> delete(String instrumentId)
        throws InstrumentNotFoundException;
}
