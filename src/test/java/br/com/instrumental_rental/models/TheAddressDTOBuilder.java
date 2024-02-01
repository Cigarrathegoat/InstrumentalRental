package br.com.instrumental_rental.models;

import br.com.instrumental_rental.controller.dto.requests.TheAddressDTO;

public class TheAddressDTOBuilder {

    public static TheAddressDTO theAddressDTOBuilder() {
        return TheAddressDTO.builder()
                .addressId(1L)
                .state("SP")
                .city("SP")
                .borough("Jardins")
                .country("Brazil")
                .streetName("Rua Rua")
                .streetNumber("1")
                .zipCode("12345678")
                .build();
    }


}
