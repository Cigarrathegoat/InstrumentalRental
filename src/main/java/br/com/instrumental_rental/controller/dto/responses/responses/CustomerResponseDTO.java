package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDTO {

    public CustomerDTO data;
}
