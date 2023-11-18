package br.com.instrumental_rental.handlers;

import br.com.instrumental_rental.controller.dto.responses.errors.ErrorResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> CustomerNotFoundExceptionHandler(
            CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("4000")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }
}
