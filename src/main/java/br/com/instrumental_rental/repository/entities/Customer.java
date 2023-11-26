package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private long customerId;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "DS_SOCIAL_SECURITY")
    private String socialSecurityNumber;//9 digits

    @Column(name = "DS_DRIVERS-LICENSE")
    private String driversLicenseNumber;//7 digits

    @Column(name = "DS_ADDRESS")
    @JoinColumn(name = "ADDRESS_DS")
    @OneToMany(mappedBy = "customer")
    private List<TheAddress> address;

    @Column(name = "DS_CONTACTS")
    @JoinColumn(name = "CONTACTS_DS")
    @OneToMany(mappedBy = "customer")
    private List<Contact> contacts;



    @Column(name = "DS_ACCOUNT_BALANCE")
    private BigDecimal accountBalance;

    @Column(name = "DS_RENTAL")
    @JoinColumn(name = "RENTAL_DS")//not necessary because mappedBy below is already doing this
    @OneToMany(mappedBy = "customer")//put in quotation marks the attribute in the Rental Table where this present Customer entity resides
    private List<Rental> rental;

    //@JoinTable(@JoinColumns "", "")
}
