package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.CustomerNotFoundException;
import br.com.instrumental_rental.repository.entities.Customer;

import java.util.List;

public interface ICustomerService extends IService<Customer, Exception> {

    List<Customer> findCustomerByName(String name) throws CustomerNotFoundException;

}
