package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.StoreDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreListResponseDTO {

    public List<StoreDTO> data;
}
