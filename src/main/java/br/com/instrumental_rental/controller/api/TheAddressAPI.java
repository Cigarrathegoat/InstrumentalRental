package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.Mappers.ITheAddressMapper;
import br.com.instrumental_rental.controller.ITheAddressAPI;
import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressResponseDTO;
import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import br.com.instrumental_rental.service.interfaces.ITheAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("V1/TheAddress")
public class TheAddressAPI implements ITheAddressAPI {

    private ITheAddressService theAddressService;

    private ITheAddressMapper theAddressMapper;

    public TheAddressAPI(ITheAddressService theAddressService, ITheAddressMapper theAddressMapper) {
        this.theAddressMapper = theAddressMapper;
        this.theAddressService = theAddressService;
    }

    @PostMapping("/add")
    public TheAddressResponseDTO add(@RequestBody TheAddressDTO theAddressDTO) {
        return TheAddressResponseDTO.builder()
                .data(theAddressMapper.convertToDto(
                                theAddressService.save(
                                        theAddressMapper.convertToEntity(
                                                theAddressDTO)
                                )
                        )
                ).build();
    }

    @GetMapping("/find/{address_id}")
    public TheAddressResponseDTO find(@PathVariable("address_id") Long theAddressID)
            throws TheAddressNotFoundException {
        return TheAddressResponseDTO.builder()
                .data(theAddressMapper.convertToDto(theAddressService.findById(theAddressID
                                )
                        )
                ).build();
    }

    @Override
    public TheAddressResponseDTO update(Long theAddressID) throws TheAddressNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<DeleteResponseDTO> delete(Long theAddressID) throws TheAddressNotFoundException {
        return null;
    }
}
