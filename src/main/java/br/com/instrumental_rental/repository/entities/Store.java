package br.com.instrumental_rental.repository.entities;


/*
@Entity
@Table(name = "TB_THEADDRESS", schema = "instrumental_rental")
@SequenceGenerator(name = "SEQ_THEADDRESS")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
 */

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_STORE", schema = "instrumental_rental")
@SequenceGenerator(schema = "instrumental_rental", name = "SEQ_STORE", sequenceName = "SEQ_STORE", allocationSize = 1)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    @Column(name = "ID_STORE")
    private Long storeId;

    @Column(name = "DS_NAME")
    private String name;

    @JoinColumn(name = "CUSTOMER_ID")
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Customer> customers;

    //TODO the strong entity doesn't need to have the weak entity in it, but the weak entity
    //TODO needs to have the strong entity as an attribute in it
    @JoinColumn(name = "ATTENDANT_ID")
   @OneToMany(cascade = CascadeType.PERSIST)
    private List<Attendant> attendants;

    //TODO find out what`s wrong here
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "STORE_INSTRUMENT", joinColumns = @JoinColumn(name = "ID_STORE"),
            inverseJoinColumns = @JoinColumn(name = "ID_INSTRUMENT"))
    private List<Instrument> instruments;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESS_ID")
    private TheAddress theAddress;

    @Column(name = "DS_CONTACTS")
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<Contact> contacts;


}
