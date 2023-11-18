package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "DS_RENTAL")
    @JoinColumn(name = "RENTAL_DS")
    @OneToMany(mappedBy = "attendant")
    private Rental rental;

    @Column(name = "DS_CONTACTS")
    @JoinColumn(name = "CONTACTS_DS")
    @OneToMany(mappedBy = "customer")
    private List<Contact> contacts;
}
