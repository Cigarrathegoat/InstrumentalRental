package br.com.instrumental_rental.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_PERSON")
@SequenceGenerator(name = "SEQ_PERSON")

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    @Column(name = "ID-PERSON")
    private long personId;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "DS_SOCIAL_SECURITY")
    private String socialSecurityNumber;//9 digits

    @Column(name = "DS_DRIVERS-LICENSE")
    private String driversLicenseNumber;//7 digits

    @Column(name = "DS_ADDRESS")
    @OneToMany(mappedBy = "person")
    private List<TheAddress> address;

    @OneToMany(mappedBy = "person")
    private List<Rental> rentals;

    @Column(name = "DS_CONTACTS")
    @OneToMany(mappedBy = "person")
    private List<Contact> contacts;

}
