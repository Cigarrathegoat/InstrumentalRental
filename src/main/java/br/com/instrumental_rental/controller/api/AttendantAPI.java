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
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public AttendantListResponseDTO find(String attendantName) throws AttendantNotFoundException {
        return AttendantListResponseDTO.builder().build();
    }

    @Override
    public AttendantListResponseDTO findAll() {
        return null;
    }

    @Override
    public AttendantResponseDTO update(Long attendantId, CustomerDTO customerDTO) throws AttendantNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(Long attendantId) throws AttendantNotFoundException {
        return null;
    }
}
