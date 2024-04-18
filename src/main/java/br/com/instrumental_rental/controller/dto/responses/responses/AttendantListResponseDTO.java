package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.AttendantDTO;
import br.com.instrumental_rental.repository.entities.Attendant;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AttendantListResponseDTO {

    public List<AttendantDTO> data;

    private String addListSuccessMessage;
}
