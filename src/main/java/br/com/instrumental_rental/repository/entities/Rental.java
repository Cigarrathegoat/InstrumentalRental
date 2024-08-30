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
@SequenceGenerator(schema = "instrumental_rental", name =  "SEQ_RENTAL",
        sequenceName = "SEQ_RENTAL", allocationSize = 1)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RENTAL")
    @Column(name = "ID_RENTAL")
    private Long rentalId;

    /*
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
     */

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "INSTRUMENT_ID")
    private Instrument instrument;

    @OneToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ATTENDANT_ID")
    private Attendant attendant;

    @Column(name = "DS_START_DATE")
    private LocalDate startDate;

    @Column(name = "DS_END_DATE")
    private LocalDate endDate;

    @Column(name = "DS_PRICE")
    private BigDecimal price;

    @Column(name = "ID_ATTENDANT_COMMISSION")
    private BigDecimal attendantCommission;

}
