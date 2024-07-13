package br.com.instrumental_rental.repository.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_THEADDRESS", schema = "instrumental_rental")
@SequenceGenerator(schema = "instrumental_rental", name = "SEQ_THEADDRESS",
        sequenceName = "SEQ_THEADDRESS", allocationSize = 1)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TheAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_THEADDRESS")
    @Column(name = "ID_ADDRESS")
    private Long addressId;

    @Column(name = "DS_STREET_NAME")
    @JsonProperty("street")
    private String streetName;

    @Column(name = "DS_STREET_NUMBER")
    private String streetNumber;

    @Column(name = "DS_BOROUGH")
    private String borough;

    @Column(name = "DS_CITY")
    private String city;

    @Column(name = "DS_STATE")
    private String state;

    @Column(name = "DS_COUNTRY")
    private String country;

    @Column(name = "DS_ZIPCODE")
    private String zipCode;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STORE_ID")
    private Store store;
}
