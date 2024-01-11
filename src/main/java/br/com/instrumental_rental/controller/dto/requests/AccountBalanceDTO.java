package br.com.instrumental_rental.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceDTO {

    /*
    @NotNull(message = "Please deposit a minimum balance of $100.00.")
    @Positive(message = "Please deposit a minimum balance of $100.00.")
    @Pattern(regexp = "^[0-9]+$", message = "Field must only have numbers")
    @Min(value = 100, message = "Please deposit a minimum balance of $100.00.")
    private long accountBalance;
    */

    @NotNull(message = "Please insert some numerical amount")
    @Positive(message = "Please insert a positive number")
    @Pattern(regexp = "^[0-9]+$", message = "please insert a numerical digit")
    private long valueToAddOrWithdraw;
}
