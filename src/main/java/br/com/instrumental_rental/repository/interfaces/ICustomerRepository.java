package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "SELECT c from Customer c" +
            "WHERE c.socialSecurityNumber = :numberProvided OR c.driversLicenseNumber = :numberProvided")
    Customer findCustomerByNumberProvided(String numberProvided);

    @Query(value = "select * from TB_CUSTOMER where DS_DRIVERS_LICENSE = :driversLicenseNumber")
    Customer findCustomerByDriversLicenseNumber(String driversLicenseNumber);
}
