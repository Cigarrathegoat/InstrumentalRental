package br.com.instrumental_rental.controller.dto.responses.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorSpecificationDTO {

    private String errorCode;

    private String errorMessage;
}
