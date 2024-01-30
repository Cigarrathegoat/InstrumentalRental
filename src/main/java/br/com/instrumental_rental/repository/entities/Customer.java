package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_CUSTOMER")
@SequenceGenerator(name = "SEQ_CUSTOMER")

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer extends Person {

    @Column(name = "DS_ACCOUNT_BALANCE")
    private BigDecimal accountBalance;

    //@JoinTable(@JoinColumns "", "")
}
