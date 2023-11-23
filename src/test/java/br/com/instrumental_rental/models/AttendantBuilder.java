package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;

public class AttendantBuilder {
    public static Attendant attendantBuilder(String id, String name, BigDecimal totalCommission) {
        return Attendant.builder().attendantId(id).name(name).totalCommission(totalCommission).build();
    }

    public static Attendant attendantBuilderNoId(String name)
    {
        return Attendant.builder().name(name).build();
    }
}
