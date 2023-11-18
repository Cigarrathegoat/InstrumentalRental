package br.com.instrumental_rental.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentDTO {

    private String type;

    private String make;

    private String model;

    private BigDecimal price;

    private LocalDate manufactureDate;

    private boolean available;
}
