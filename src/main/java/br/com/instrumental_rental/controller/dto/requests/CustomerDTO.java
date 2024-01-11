package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.customvalidators.IOverage;
import br.com.instrumental_rental.repository.entities.Contact;
import br.com.instrumental_rental.repository.entities.TheAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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

    private Long customerId;

    @NotBlank(message = "Name field must not be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "only letter-type characters allowed in name field")
    private String name;

    private List<TheAddress> addresses;

    private List<Contact> contacts;

    @NotBlank(message = "name field must not be empty")
    @Pattern(regexp = "^[0-9]+$", message = "please enter only numeric digits in the Date of birth field")
    @Range(min = 8, max = 8, message = "please enter a valid date in the mm-dd-yyyy format")
    @IOverage(message = "must be at least 18 years old")
    private LocalDate dateOfBirth;

}
