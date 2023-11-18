package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.repository.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendantDTO {

    private String name;

    private List<Contact> contacts;
}
