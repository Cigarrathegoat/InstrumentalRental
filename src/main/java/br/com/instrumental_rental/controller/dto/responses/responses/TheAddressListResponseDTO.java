package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TheAddressListResponseDTO {

    public List<TheAddressDTO> data;

    public String addressListAdded;
}
