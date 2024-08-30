package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Rental;
import br.com.instrumental_rental.repository.interfaces.ICustomerRepository;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.instrumental_rental.service.util.ServiceUtil.sufficientBalanceChecker;

@Service
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepositoryAttribute;

    @Autowired
    private CustomerService(ICustomerRepository customerRepositoryParameter) {
        this.customerRepositoryAttribute = customerRepositoryParameter;
    }

    public Customer findCustomerById(Long customerId) throws CustomerNotFoundException {
        return customerRepositoryAttribute.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                                "C01", "Customer not found"
                        )
                );
    }


    @Override
    public List<Customer> saveFirstTime(List<Customer> customerList)
            throws StoreNotFoundException, CustomerNotFoundException {
        List<Customer> savedCustomers = new ArrayList<>();
        for (Customer customer : customerList) {
            savedCustomers.add(customerRepositoryAttribute.save(customer));
        }
        return savedCustomers;
    }

    /*
    1. save customer
    2. take rentalList and save all rentals (do a for on this one;
    3. create method to updateCustomerBalance, include save in it of course (use customer.setBalance);
    4. save instrument (customer.getRental().getInstrument(instrument));
    5. make method to calculate commission(customer.getRental().getAttendant().setComission(comission);
     */

    @Override
    public Customer save(Customer customer) throws StoreNotFoundException, CustomerNotFoundException {
        return customerRepositoryAttribute.save(customer);
    }

    @Override
    public Customer findCustomerByNumberProvided(String number) throws CustomerNotFoundException {
        var customerSought = customerRepositoryAttribute.findCustomerByNumberProvided(number);
        if (customerSought == null) {
            throw new CustomerNotFoundException("C01", "Customer not found");
        } else {
            return customerSought;
        }
    }


    @Override
    public BigDecimal addToBalance(Long customerId, BigDecimal addition)
            throws CustomerNotFoundException {
        var customerFound = findCustomerById(customerId);
        customerFound.setAccountBalance(customerFound.getAccountBalance().add(addition));
        return customerFound.getAccountBalance();
    }

    @Override
    public BigDecimal withdraw(Long customerId, BigDecimal withdrawal)
            throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException {
        var withdrawer = findCustomerById(customerId);
        sufficientBalanceChecker(withdrawer, withdrawal);
        withdrawer.setAccountBalance(
                withdrawer.getAccountBalance().subtract(withdrawal));
        customerRepositoryAttribute.save(withdrawer);

        return withdrawer.getAccountBalance();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepositoryAttribute.findAll();
    }

    @Override
    public void delete(Long customerId) throws CustomerNotFoundException {
        var customerToDelete = findCustomerById(customerId);
        customerRepositoryAttribute.delete(customerToDelete);
    }

    @Override
    public Customer update(Customer customer) throws CustomerNotFoundException {
        var customerToUpdate = findCustomerById(customer.getPersonId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setDateOfBirth(customer.getDateOfBirth());
        customerToUpdate.setContacts(customer.getContacts());
        customerRepositoryAttribute.save(customerToUpdate);
        return customerToUpdate;
    }
}
