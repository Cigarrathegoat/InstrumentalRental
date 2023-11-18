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

    @Column(name = "ID_ADDRESS")
    private String addressId;

    @Column(name = "DS_STREETNAME")
    private String streetName;

    @Column(name = "DS_STREETNUMBER")
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

    @Column(name = "ID_CUSTOMER")
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
}
