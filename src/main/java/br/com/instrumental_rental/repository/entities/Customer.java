package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_CUSTOMER")
@SequenceGenerator(name = "SEQ_CUSTOMER")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUSTOMER")
    @Column(name = "ID_CUSTOMER")
    private String customerId;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "DS_ACCOUNT_BALANCE")
    private BigDecimal accountBalance;
}
