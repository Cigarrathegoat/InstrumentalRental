package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findCustomerByName(String customerName);
}
