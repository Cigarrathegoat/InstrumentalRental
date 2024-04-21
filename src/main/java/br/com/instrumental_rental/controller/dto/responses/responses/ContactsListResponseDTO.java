package br.com.instrumental_rental.controller.dto.responses.responses;

import br.com.instrumental_rental.controller.dto.requests.ContactsDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ContactsListResponseDTO {

    public List<ContactsDTO> data;

    public String addListSuccessMessage;
}
