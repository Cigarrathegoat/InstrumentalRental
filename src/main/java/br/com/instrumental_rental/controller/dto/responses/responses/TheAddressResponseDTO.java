package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheAddressResponseDTO {

    public TheAddressDTO data;
}
