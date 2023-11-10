package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;

public class AttendantBuilder {
    public static Attendant attendantBuilder(String id, String name) {
        return Attendant.builder().attendantId(id).name(name).build();
    }

    public static Attendant attendantBuilderNoId(String name)
    {
        return Attendant.builder().name(name).build();
    }
}
