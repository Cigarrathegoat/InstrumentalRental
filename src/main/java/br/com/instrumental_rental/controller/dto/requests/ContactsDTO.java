package br.com.instrumental_rental.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactsDTO {

    @NotBlank(message = "field must not be empty")
    private String contactType;

    @NotBlank(message = "field must not be empty")
    private String contactContent;
}
