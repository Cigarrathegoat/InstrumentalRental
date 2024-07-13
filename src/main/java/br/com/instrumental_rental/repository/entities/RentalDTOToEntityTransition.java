package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/*
@Table(name = "TB_STORE", schema = "instrumental_rental")
@SequenceGenerator(schema = "instrumental_rental", name = "SEQ_STORE", sequenceName = "SEQ_STORE", allocationSize = 1)

 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RentalDTOToEntityTransition {


    private Long rentalId;


    private Long customerId;


    private Long instrumentId;


    private LocalDate startDate;


    private LocalDate endDate;


    private BigDecimal price;


    private Long attendantId;


    private BigDecimal attendantCommission;


}
