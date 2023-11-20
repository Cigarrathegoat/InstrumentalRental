package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.repository.entities.Contact;
import br.com.instrumental_rental.repository.entities.TheAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "Name field must not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "only letter-type characters allowed in name field")
    private String name;

    private List<TheAddress> addresses;

    private List<Contact> contacts;

    private LocalDate dateOfBirth;

}
