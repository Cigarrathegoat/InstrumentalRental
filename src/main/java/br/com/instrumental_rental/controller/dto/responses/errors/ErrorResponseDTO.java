package br.com.instrumental_rental.controller.dto.responses.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {

    private ErrorSpecificationDTO data;
}
