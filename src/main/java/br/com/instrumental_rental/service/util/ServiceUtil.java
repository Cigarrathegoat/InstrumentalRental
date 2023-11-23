package br.com.instrumental_rental.service.util;

import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;

public class ServiceUtil {

    public static void sufficientBalanceChecker(Customer customer, BigDecimal withdrawal)
            throws WithdrawalGreaterThanBalanceException {
        if (withdrawal.compareTo(customer.getAccountBalance()) > 0) {
            throw new WithdrawalGreaterThanBalanceException(
                    "W01", "Withdrawal greater than balance");
        }
    }
}
