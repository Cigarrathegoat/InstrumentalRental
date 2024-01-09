package br.com.instrumental_rental.controller.dto.requests;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {


    /* @NotBlank(message = "field cannot be left blank")
    @Pattern(regexp = "^[a-zA-Z]+&", message = "please use only letters")
    private String type;

    @NotBlank(message = "field cannot be left blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "must only use letters")
    private String make;

    @NotBlank(message = "field cannot be left blank")
    private String model;

    @NotBlank(message = "field cannot be left blank")
    @Pattern(regexp = "^[0-9]+$", message = "only numeral digits please")
    private BigDecimal price;

    @NotBlank(message = "field cannot be left blank")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "date must be in mm-dd-YYYY format")
    private LocalDate manufactureDate;

     */

    @NotBlank(message = "field cannot be left blank")


    private String customerId;

    @NotBlank(message = "field cannot be left blank")
     private String attendantId;

    @NotBlank(message = "field cannot be left blank")
    private InstrumentDTO instrument;

    @NotBlank(message = "field cannot be left blank")
    private LocalDate startDate;

    @NotBlank(message = "field cannot be left blank")
    private LocalDate endDate;

    @NotBlank(message = "field cannot be left blank")
    private BigDecimal price;
}
