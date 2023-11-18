package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendantResponseDTO {

    public AttendantDTO data;
}
