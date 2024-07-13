package br.com.instrumental_rental.repository.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_PERSON", schema = "instrumental_rental")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "PERSON_TYPE", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(schema ="instrumental_rental", name = "SEQ_PERSON",
        sequenceName = "SEQ_PERSON", allocationSize = 1)

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    @Column(name = "ID_PERSON")
    private long personId;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "DS_SOCIAL_SECURITY")
    private String socialSecurityNumber;//9 digits

    @Column(name = "DS_DRIVERS_LICENSE")
    private String driversLicenseNumber;//7 digits

    @Column(name = "DS_ADDRESS")
    @OneToMany(mappedBy = "person",  cascade = CascadeType.MERGE)
    private List<TheAddress> address;

    @Column(name = "DS_CONTACTS")
    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private List<Contact> contacts;

}
