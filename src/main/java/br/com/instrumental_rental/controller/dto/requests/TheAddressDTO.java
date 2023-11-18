package br.com.instrumental_rental.controller.dto.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheAddressDTO {

    private String streetName;

    private String streetNumber;

    private String borough;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
