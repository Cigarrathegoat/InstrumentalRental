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
@Table(name = "TB_CUSTOMER", schema = "instrumental_rental")

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

    @Column(name = "DS_ACCOUNT_BALANCE")
    private BigDecimal accountBalance;

    //TODO @ManyToOne doesn`t need to go on the entity, but its corresponding @OneToMany does (on the  corresponding entity)

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_RENTAL")
    private List<Rental> rentals;

    /*
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STORE_ID")
    private Store store;

     */
}
