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

public class AttendantAPI implements IAttendantAPI {

    private IAttendantService attendantService;

    private IAttendantMapper attendantMapper;

    @Autowired
    public AttendantAPI(IAttendantService attendantService, IAttendantMapper attendantMapper) {
        this.attendantMapper = attendantMapper;
        this.attendantService = attendantService;
    }
    @Override
    public AttendantResponseDTO add(AttendantDTO attendantDTO) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public AttendantListResponseDTO find(String attendantName) throws AttendantNotFoundException {
        return null;
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
