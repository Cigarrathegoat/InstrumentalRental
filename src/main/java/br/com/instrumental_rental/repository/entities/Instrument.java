package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_INSTRUMENT")
@SequenceGenerator(name = "SEQ_INSTRUMENT")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INSTRUMENT")
    @Column(name = "ID_INSTRUMENT")
    private String instrumentId;

    @Column(name = "DS_MAKE")
    private String make;

    @Column(name = "DS_MODEL")
    private String model;

    @Column(name = "DS_PRICE")
    private BigDecimal price;

    @Column(name = "DS_MANUFACURE_DATE")
    private LocalDate manufactureDate;

    @Column(name = "DS_AVAILABLE")
    private boolean available;

}
