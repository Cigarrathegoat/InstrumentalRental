package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c from Customer c" +
            "WHERE c.socialSecurityNumber = :numberProvided OR c.driversLicenseNumber = :numberProvided")
    Customer findCustomerByNumberProvided(String numberProvided);


    @Query(value = "SELECT C.customerId, COUNT(R.rentalId) AS total_rentals FROM Rental" +
            "R JOIN Customer C on R.customerId = C.customerId" +
    "WHERE YEAR(R.rental_date) = <2023> GROUP BY C.customerId" +
    "AND C.customerId = <:customerId> GROUP BY C.customerId")
    Customer allRentalsMadeByOneCustomer(Long customerId);
    //TODO: write query to find total of rents paid by one particular client per year

    //TODO: make an INSERT query to put a list of customer/attendant/instruments into the database at once


}
