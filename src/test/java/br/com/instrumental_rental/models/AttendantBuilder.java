package br.com.instrumental_rental.models;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;

public class AttendantBuilder {

    /*private Attendant attendantBuilder = AttendantBuilder.attendantBuilder(
            "1", "Mark", BigDecimal.valueOf(0));*/
    public static Attendant attendantBuilder(String id, String name, BigDecimal totalCommission) {
        return Attendant.builder().attendantId("01").name("Mark")
                .totalCommission(BigDecimal.valueOf(0))
                .build();
    }

    public static Attendant attendantBuilderNoId(String name) {
        return Attendant.builder().attendantId(null).name("Mark")
                .totalCommission(BigDecimal.valueOf(0)).build();
    }
}
