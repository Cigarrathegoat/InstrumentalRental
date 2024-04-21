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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IContactAPI {

    @ApiOperation(value = "add Contacts object",
    response = ContactsResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ContactsResponseDTO add(ContactsDTO contactsDTO);

    /*
    @ApiOperation(value = "Add list of Attendant objects",
            response = AttendantListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<AttendantListResponseDTO>
    addList(@RequestBody List<AttendantDTO> attendantDTOList);
     */

    @ApiOperation(value = "add a list of Contact objects",
    response = ContactsListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<ContactsListResponseDTO>
    addList(@RequestBody List<ContactsDTO> contactsDTOList);

    @ApiOperation(value = "find API object",
    response = ContactsListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ContactsListResponseDTO find(String holder) throws ContactNotFoundException;

    @ApiOperation(value = "update Contacts object",
    response = ContactsResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ContactsResponseDTO update(Long contactsId, ContactsDTO contactsDTO)
            throws ContactNotFoundException;

    /*
    public ContactsResponseDTO update(@PathVariable("contactsId") Long contactsId,
                                      @RequestBody ContactsDTO contactsDTO) throws ContactNotFoundException
     */
    @ApiOperation(value = "delete Contacts object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    ResponseEntity<DeleteResponseDTO> delete(Long contactsId) throws ContactNotFoundException;
}
