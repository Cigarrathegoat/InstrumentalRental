package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.repository.entities.Contact;
import lombok.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendantDTO {

    @NotBlank(message = "Name field must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must only have letters")
    private String name;

    @NotBlank(message = "Contacts field must not be blank")
    private List<ContactsDTO> contacts;
}
