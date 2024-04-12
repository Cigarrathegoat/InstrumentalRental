package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService {

    List<Customer> save(List<Customer> customerList);

    List<Customer> findAll();

    void delete(Long customerId) throws CustomerNotFoundException;

    Customer update(Customer customer) throws CustomerNotFoundException;

    Customer findCustomerByNumberProvided(String number) throws CustomerNotFoundException;

    BigDecimal addToBalance(Long customerId, BigDecimal addition)
            throws CustomerNotFoundException;

    BigDecimal withdraw(Long customerId, BigDecimal withdrawal)
        throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException;

    Customer findCustomerById(Long customerId) throws CustomerNotFoundException;

}
