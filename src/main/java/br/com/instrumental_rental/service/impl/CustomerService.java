package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.exceptions.WithdrawalGreaterThanBalanceException;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.interfaces.ICustomerRepository;
import br.com.instrumental_rental.service.AbstractValidateService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final IInstrumentService instrumentService;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository,
                           IInstrumentService instrumentService) {
        this.customerRepository = customerRepository;
        this.instrumentService = instrumentService;
    }

    private Customer finder(String customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                                "C01", "Customer not found"
                        )
                );
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByNumberProvided(String number) throws CustomerNotFoundException {
        var customerSought = customerRepository.findCustomerByNumberProvided(number);
        if (customerSought == null) {
            throw new CustomerNotFoundException("C01", "Customer not found");
        } else {
            return customerSought;
        }
    }

    @Override
    public BigDecimal addToBalance(String customerId, BigDecimal addition)
            throws CustomerNotFoundException {
        var customerFound = finder(customerId);
        customerFound.setAccountBalance(customerFound.getAccountBalance().add(addition));
        return customerFound.getAccountBalance();
    }

    @Override
    public BigDecimal withdraw(String customerId, BigDecimal withdrawal)
            throws CustomerNotFoundException, WithdrawalGreaterThanBalanceException {
        var withdrawer = finder(customerId);
        sufficientBalanceChecker(withdrawer, withdrawal);
            withdrawer.setAccountBalance(
                    withdrawer.getAccountBalance().subtract(withdrawal));
            customerRepository.save(withdrawer);

        return withdrawer.getAccountBalance();
    }


    private void sufficientBalanceChecker(Customer customer, BigDecimal withdrawal)
    throws WithdrawalGreaterThanBalanceException{
        if (withdrawal.compareTo(customer.getAccountBalance()) > 0) {
            throw new WithdrawalGreaterThanBalanceException(
                    "W01", "Withdrawal greater than balance");
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Customer customer) throws CustomerNotFoundException {
        var customerToDelete = finder(customer.getCustomerId());
        customerRepository.delete(customerToDelete);
    }

    @Override
    public Customer update(Customer customer) throws CustomerNotFoundException {
        var customerToUpdate = finder(customer.getCustomerId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setDateOfBirth(customer.getDateOfBirth());
        customerToUpdate.setContacts(customer.getContacts());
        customerRepository.save(customerToUpdate);
        return customerToUpdate;
    }
}
