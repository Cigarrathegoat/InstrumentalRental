package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_ATTENDANT")
@SequenceGenerator(name = "SEQ_ATTENDANT")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Attendant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATTENDANT")
    @Column(name = "ID_ATTENDANT")
    private String attendantId;

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

    @Column(name = "DS_TOTAL_COMMISSION")
    private BigDecimal totalCommission;
}
