package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.customvalidators.IOverage;
import br.com.instrumental_rental.repository.entities.Contact;
import lombok.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.metadata.ConstraintDescriptor;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendantDTO {

    private Long attendantId;

    @NotBlank(message = "Name field must not be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Field must only have letters")
    private String name;

    @NotBlank(message = "dateOfBirth field must not be blank")
    @Pattern(regexp = "^[0-9]+$", message = "field must only have numbers")
    @Positive(message = "numbers in field must be positive")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "date entered must be in dd/mm/yyyy format")
    @IOverage(message = "attendant must be at least 18 years old")
    private LocalDate birthDate;

    @NotBlank(message = "socialSecurityNumber field must not be blank")
    @Pattern(regexp = "^[0-9]+$", message = "please only input numeral digits")
    @Size(min = 9, max = 9, message = "social security number must have exactly nine digits")
    private String socialSecurityNumber;

    @NotBlank(message = "driver's license number field must not be left blank")
    @Pattern(regexp = "^[0-9]+$", message = "please only input numerical digits")
    @Size(min = 7, max = 7, message = "driver's license number must have exactly seven digits")
    private String driversLicense;


    @NotBlank(message = "Contacts field must not be blank")
    private List<ContactsDTO> contacts;
}
