package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.interfaces.ICustomerRepository;
import br.com.instrumental_rental.service.AbstractValidateService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    AbstractValidateService abstractValidateService;

    @Override
    public List<Customer> findCustomerByName(String name) throws CustomerNotFoundException {
        var customerSought = customerRepository.findCustomerByName(name);
        if (customerSought.isEmpty()) {
            throw new CustomerNotFoundException("C01", "Customer not found");
        } else {
            return customerSought;
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) throws CustomerNotFoundException {
        var customerToDelete = customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "C01", "Customer not found"
                )
                );
        customerRepository.delete(customerToDelete);

    }

    @Override
    public Customer update(Customer customer) throws CustomerNotFoundException {
        var customerToUpdate = customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                                "C01", "Customer not found"
                        )
                );
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAccountBalance(customer.getAccountBalance());
        customerToUpdate.setDateOfBirth(customer.getDateOfBirth());
        customerRepository.save(customerToUpdate);
        return customerToUpdate;
    }
}
