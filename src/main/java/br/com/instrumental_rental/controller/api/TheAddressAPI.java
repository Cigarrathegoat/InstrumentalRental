package br.com.instrumental_rental.controller.api;

import br.com.instrumental_rental.controller.ITheAddressAPI;
import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.DeleteResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.responses.TheAddressResponseDTO;
import br.com.instrumental_rental.exceptions.TheAddressNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("V1/TheAddress")
public class TheAddressAPI implements ITheAddressAPI {
    @
    public TheAddressResponseDTO add(TheAddressDTO theAddressDTO) {
        return null;
    }

    @Override
    public TheAddressResponseDTO find(Long theAddressID) throws TheAddressNotFoundException {
        return null;
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
