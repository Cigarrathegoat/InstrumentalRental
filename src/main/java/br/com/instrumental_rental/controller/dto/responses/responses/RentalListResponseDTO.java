package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.RentalDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RentalListResponseDTO {

    public List<RentalDTO> data;

    public String addListMessage;
}
