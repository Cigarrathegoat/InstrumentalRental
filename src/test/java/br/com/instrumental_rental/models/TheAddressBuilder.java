package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.TheAddress;

public class TheAddressBuilder {

    public TheAddress theAddressBuilder() {
        return TheAddress.builder()
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

    public TheAddress theAddressNoIdBuilder() {
        return TheAddress.builder()
                .addressId(null)
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
