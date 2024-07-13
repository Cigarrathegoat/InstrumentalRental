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
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("V1/attendant")
public class AttendantAPI implements IAttendantAPI {

    private final IAttendantService attendantService;

    private final IAttendantMapper attendantMapper;

   @Autowired
    public AttendantAPI(IAttendantService attendantService, IAttendantMapper attendantMapper) {
        this.attendantMapper = attendantMapper;
        this.attendantService = attendantService;
    }

    @PostMapping("/new")
    public AttendantResponseDTO add(@RequestBody AttendantDTO attendantDTO)
            throws StoreNotFoundException, AttendantNotFoundException {
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

    @PostMapping("/new_list")
    public ResponseEntity<AttendantListResponseDTO> addList(
            @RequestBody List<AttendantDTO> attendantDTOList
    ) throws StoreNotFoundException, AttendantNotFoundException {
       attendantService.saveFirstTime(attendantMapper.convertToEntityList(attendantDTOList));
       return ResponseEntity.ok(
               AttendantListResponseDTO.builder()
                       .addListSuccessMessage(
                               "List successfully added"
                       )
                       .build()
       );
    }

    @GetMapping("/find/{attendant}")
    public AttendantResponseDTO find(@PathVariable("attendant") String attendantNumber)
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

    @PutMapping("/update/{attendantId}")
    public AttendantResponseDTO update(@PathVariable("attendantId") Long attendantId,
                                       @RequestBody AttendantDTO attendantDTO) throws AttendantNotFoundException {
        return AttendantResponseDTO.builder()
                .data(
                        attendantMapper.convertToDTO(
                                attendantService.update(attendantMapper.convertToEntity(attendantDTO
                                        )
                                )
                        )
                ).build();
    }

    @DeleteMapping("/delete/{attendantId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("attendantId") Long attendantId)
            throws AttendantNotFoundException {
        attendantService.delete(attendantId);
        return ResponseEntity.ok(DeleteResponseDTO
                .builder()
                .deleteSuccessMessage("Attendant successfully deleted")
                .build());
    }
}
