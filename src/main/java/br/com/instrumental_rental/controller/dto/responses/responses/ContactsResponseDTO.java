package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactsResponseDTO {

    public ContactsDTO data;
}
