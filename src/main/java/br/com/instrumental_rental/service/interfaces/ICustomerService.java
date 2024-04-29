package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Rental;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService {

    List<Customer> saveFirstTime(List<Customer> customerList);

    Customer save(Customer customer);

    List<Customer> findAll();

    void delete(Long customerId) throws CustomerNotFoundException;

    Customer update(Customer customer) throws CustomerNotFoundException;

    Customer findCustomerByNumberProvided(String number) throws CustomerNotFoundException;

    BigDecimal addToBalance(Long customerId, BigDecimal addition)
            throws CustomerNotFoundException;

    BigDecimal withdraw(Long customerId, BigDecimal withdrawal)
        throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException;

    Customer findCustomerById(Long customerId) throws CustomerNotFoundException;

    void addToRentals (Long customerId, Long rentalId) throws CustomerNotFoundException,
            RentalNotFoundException;

    void addToStore(Long customerId, Long storeId) throws CustomerNotFoundException,
            StoreNotFoundException;

}
