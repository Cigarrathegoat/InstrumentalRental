package br.com.instrumental_rental.repository.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TB_THEADDRESS")
@SequenceGenerator(name = "SEQ_THEADDRESS")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TheAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @Column(name = "ID_ADDRESS")
    private Long addressId;

    @Column(name = "DS_STREET_NAME")
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

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}
