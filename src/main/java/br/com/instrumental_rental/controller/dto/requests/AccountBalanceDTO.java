package br.com.instrumental_rental.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceDTO {

    @NotNull(message = "Please deposit a minimum balance of $100.00.")
    @Positive(message = "Please deposit a minimum balance of $100.00.")
    @Min(value = 100, message = "Please deposit a minimum balance of $100.00.")
    private long accountBalance;
}
