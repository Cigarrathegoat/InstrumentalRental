package br.com.instrumental_rental.controller.dto.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@Builder
public class TheAddressDTO {

    @NotBlank(message = "field must not be blank")
    @Pattern(regexp  = "^[a-zA-Z]+$", message = "field must be letters only")
    private String streetName;

    @NotBlank(message = "field must not be blank")
    @Pattern(regexp = "^[0-9]+$", message = "field must be numbers only")
    @Positive(message = "field must be a positive number")
    private String streetNumber;


    private String borough;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
