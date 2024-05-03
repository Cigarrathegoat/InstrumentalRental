package br.com.instrumental_rental.controller;

import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IAttendantAPI {

    @ApiOperation(value = "add Attendant object",
            response = AttendantDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    AttendantResponseDTO add(AttendantDTO attendantDTO) throws StoreNotFoundException, AttendantNotFoundException;

    @ApiOperation(value = "Add list of Attendant objects",
            response = AttendantListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<AttendantListResponseDTO>
    addList(@RequestBody List<AttendantDTO> attendantDTOList) throws StoreNotFoundException, AttendantNotFoundException;

    @ApiOperation(value = "find Attendant object",
            response = AttendantResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public AttendantResponseDTO find(String attendantNumber)
            throws AttendantNotFoundException;

    @ApiOperation(value = "list all Attendant objects",
            response = AttendantListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public AttendantListResponseDTO listAll();

    @ApiOperation(value = "update Attendant object",
            response = AttendantListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public AttendantResponseDTO update(Long attendantId, AttendantDTO attendantDTO)
            throws AttendantNotFoundException;

    @ApiOperation(value = "delete Attendant object",
            response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public ResponseEntity<DeleteResponseDTO> delete(Long attendantId)
            throws AttendantNotFoundException;
}
