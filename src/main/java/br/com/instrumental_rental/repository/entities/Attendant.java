package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_ATTENDANT", schema = "instrumental_rental")

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Attendant extends Person {

    @Column(name = "DS_TOTAL_COMMISSION")
    private BigDecimal totalCommission;

    /*
    @ManyToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STORE_ID")
    private Store store;


    @OneToMany(mappedBy = "attendant")
    private List<Rental> rentals;
    */

}
