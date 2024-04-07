package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_RENTAL", schema = "instrumental_rental")
@SequenceGenerator(name = "SEQ_RENTAL")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RENTAL")
    @Column(name = "ID_RENTAL")
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "INSTRUMENT_ID")
    private Instrument instrument;

    @Column(name = "DS_START_DATE")
    private LocalDate startDate;

    @Column(name = "DS_END_DATE")
    private LocalDate endDate;

    @Column(name = "DS_PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ATTENDANT_ID")
    private Attendant attendant;

    @Column(name = "ID_ATTENDANT_COMMISSION")
    private BigDecimal attendantCommission;

}
