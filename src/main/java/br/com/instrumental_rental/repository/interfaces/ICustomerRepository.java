package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c from Customer c" +
            "WHERE c.socialSecurityNumber = :numberProvided OR c.driversLicenseNumber = :numberProvided")
    Customer findCustomerByNumberProvided(String numberProvided);


    //TODO: write query to find total of rents paid by one particular client per year

    //TODO: make an INSERT query to put a list of customer/attendant/instruments into the database at once


}
