package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_RENTAL")
@SequenceGenerator(name = "SEQ_RENTAL")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RENTAL")
    @Column(name = "ID_RENTAL")
    private String rentalId;

    @Column(name = "ID_CUSTOMER")//the name of the column of course
    @JoinColumn(name = "CUSTOMER_ID")
    //the name that will show on the Rental Table referring to the customerId column maybe don't put joincolumn here 'cause if it's somewhere else we're good for obvious reasons, plus it's easier to maintain
    private Customer customer;

    @Column(name = "ID_INSTRUMENT")
    @JoinColumn(name = "INSTRUMENT_ID")
    private Instrument instrument;

    @Column(name = "DS_START_DATE")
    private LocalDate startDate;

    @Column(name = "DS_END_DATE")
    private LocalDate endDate;

    @Column(name = "DS_PRICE")
    private BigDecimal price;

    @Column(name = "ID_ATTENDANT")
    @JoinColumn(name = "ATTENDANT_ID")
    private Attendant attendant;

    @Column(name = "ID_ATTENDANT_COMMISSION")
    private BigDecimal attendantCommission;

}
