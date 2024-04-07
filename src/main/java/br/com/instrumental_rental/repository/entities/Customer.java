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
@DiscriminatorValue("CUSTOMER")
@SequenceGenerator(name = "SEQ_CUSTOMER", sequenceName = "SEQ_CUSTOMER", allocationSize = 1)

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

    @Column(name = "DS_ACCOUNT_BALANCE")
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;


    //@JoinTable(@JoinColumns "", "")
}
