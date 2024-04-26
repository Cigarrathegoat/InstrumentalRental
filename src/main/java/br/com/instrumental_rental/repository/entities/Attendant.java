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
@SequenceGenerator(name = "SEQ_ATTENDANT", sequenceName = "SEQ_ATTENDANT", allocationSize = 1)

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Attendant extends Person {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATTENDANT")
    @Column(name = "ID_ATTENDANT")
    private Long attendantId;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "DS_SOCIAL_SECURITY")
    private String socialSecurityNumber;//9 digits

    @Column(name = "DS_DRIVERS-LICENSE")
    private String driversLicenseNumber;//7 digits

    @Column(name = "DS_RENTAL")
    @JoinColumn(name = "RENTAL_DS")
    @OneToMany(mappedBy = "attendant")
    private Rental rental;

    @Column(name = "DS_CONTACTS")
    @JoinColumn(name = "CONTACTS_DS")
    @OneToMany(mappedBy = "customer")
    private List<Contact> contacts;

     */

    @OneToMany(mappedBy = "attendant")
    private List<Rental> rentals;


    @Column(name = "DS_TOTAL_COMMISSION")
    private BigDecimal totalCommission;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;
}
