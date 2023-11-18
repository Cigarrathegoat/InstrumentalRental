package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.InstrumentDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InstrumentListResponseDTO {

    public List<InstrumentDTO> data;
}
