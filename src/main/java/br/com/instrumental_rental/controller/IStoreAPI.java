package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.StoreDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.StoreResponseDTO;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IStoreAPI {

    @ApiOperation(value = "add Store object",
            response = StoreResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message =  "not found")})
    ResponseEntity<StoreResponseDTO> add(StoreDTO storeDTO) throws StoreNotFoundException;


}
