package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.AccountBalanceDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBalanceResponseDTO {

    public AccountBalanceDTO data;
}
