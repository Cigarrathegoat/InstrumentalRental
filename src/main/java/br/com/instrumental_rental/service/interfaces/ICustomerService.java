package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService {

    public Customer save(Customer customer);

    public List<Customer> findAll();
    public void delete(Customer customer) throws CustomerNotFoundException;

    public Customer update(Customer customer) throws CustomerNotFoundException;

    Customer findCustomerByNumberProvided(String number) throws CustomerNotFoundException;

    BigDecimal addToBalance(String customerId, BigDecimal addition)
            throws CustomerNotFoundException;

    BigDecimal withdraw(String customerId, BigDecimal withdrawal)
        throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException;

    Customer findById(String customerId) throws CustomerNotFoundException;

}
