package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService extends IService<Customer, Exception> {

    Customer findCustomerByNumberProvided(String number) throws CustomerNotFoundException;

    BigDecimal addToBalance(String customerId, BigDecimal addition)
            throws CustomerNotFoundException;

    BigDecimal withdraw(String customerId, BigDecimal withdrawal)
        throws CustomerNotFoundException;

}
