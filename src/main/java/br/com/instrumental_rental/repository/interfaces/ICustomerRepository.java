package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "select * from TB_CUSTOMER where DS_SOCIAL_SECURITY = :socialSecurityNumber")
    Customer findCustomerBySocialSecurityNumber(String socialSecurityNumber);

    @Query(value = "select * from TB_CUSTOMER where DS_DRIVERS_LICENSE = :driversLicenseNumber")
    Customer findCustomerByDriversLicenseNumber(String driversLicenseNumber);
}
