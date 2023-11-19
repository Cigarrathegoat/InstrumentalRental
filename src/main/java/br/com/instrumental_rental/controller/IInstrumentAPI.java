package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.InstrumentDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.InstrumentResponseDTO;
import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IInstrumentAPI {

    @ApiOperation(value = "Add Instrument object",
    response = InstrumentResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    InstrumentResponseDTO add(InstrumentDTO instrumentDTO) throws InstrumentNotFoundException;

    @
}
