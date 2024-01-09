package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.IAttendantMapper;
import br.com.instrumental_rental.controller.IAttendantAPI;
import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;
import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantListResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.AttendantResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/attendant")
public class AttendantAPI implements IAttendantAPI {

    private IAttendantService attendantService;

    private IAttendantMapper attendantMapper;

    @Autowired
    public AttendantAPI(IAttendantService attendantService, IAttendantMapper attendantMapper) {
        this.attendantMapper = attendantMapper;
        this.attendantService = attendantService;
    }
    @PostMapping("/new")
    public AttendantResponseDTO add(@RequestBody AttendantDTO attendantDTO)
            throws AttendantNotFoundException {
        return AttendantResponseDTO.builder()
                .data(
                        attendantMapper.convertToDTO(
                                attendantService.save(
                                        attendantMapper.convertToEntity(
                                                attendantDTO)
                                )
                        )
                ).build();
    }

    @GetMapping("/find/{attendant}")
    public AttendantResponseDTO find(@PathVariable("attendant")String attendantNumber)
            throws AttendantNotFoundException {

        return AttendantResponseDTO.builder()
                .data(
                        attendantMapper.convertToDTO(attendantService.findAttendantByNumberProvided(
                                attendantNumber)
                        )
                ).build();
    }

    @GetMapping("/listAll")
    public AttendantListResponseDTO listAll() {

        return AttendantListResponseDTO.builder()
                .data(
                        attendantMapper.convertToListDto(attendantService.findAll())
                ).build();
    }

    @PutMapping("/update/attendant/{attendantId}")
    public AttendantResponseDTO update(@PathVariable("attendantId")Long attendantId,
                                       @RequestBody AttendantDTO attendantDTO) throws AttendantNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(Long attendantId) throws AttendantNotFoundException {
        return null;
    }
}
