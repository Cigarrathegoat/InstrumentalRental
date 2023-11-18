package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.repository.entities.Contact;
import br.com.instrumental_rental.repository.entities.TheAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String name;

    private List<TheAddress> addresses;

    private List<Contact> contacts;

    private LocalDate dateOfBirth;

}
