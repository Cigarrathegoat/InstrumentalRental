package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
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
public class RentalDTO {

    private String customerId;

     private String attendantId;

    private InstrumentDTO instrument;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal price;
}
