package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.CustomerDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerListResponseDTO {

    public List<CustomerDTO> data;
}
