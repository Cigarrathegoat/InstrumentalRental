package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.RentalDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalResponseDTO {

    public RentalDTO data;
}
