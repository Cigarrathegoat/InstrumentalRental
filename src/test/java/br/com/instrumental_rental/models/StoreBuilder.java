package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.*;

import java.util.List;

public class StoreBuilder {

    //public final idNumber = 1L;

    public static Store storeNoIdBuilder() {
        return Store.builder().attendants(List.of(AttendantBuilder.attendantBuilder()))
                .customers(List.of(CustomerBuilder.customerBuilder()))
                .instruments(List.of(InstrumentBuilder.instrumentBuilder()))
                .theAddress(TheAddressBuilder.theAddressBuilder())
                .contacts(List.of(ContactBuilder.contactBuilder())).build();
    }

    public static Store storeBuilder() {
        return Store.builder().storeId(1L).attendants(List.of(AttendantBuilder.attendantBuilder()))
                .customers(List.of(CustomerBuilder.customerBuilder()))
                .instruments(List.of(InstrumentBuilder.instrumentBuilder()))
                .theAddress(TheAddressBuilder.theAddressBuilder())
                .contacts(List.of(ContactBuilder.contactBuilder())).build();
    }
}
