package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRentalRepository extends JpaRepository<Rental, Long> {

   @Query(value = "SELECT r FROM Rental r" +
   "WHERE r.customer = :word OR r.attendant = :word OR r.instrument = :word")
    List<Rental> findRentalByWord(String word);


   //TODO find all rentals for a particular instrument type

    //TODO find all rentals that have not been paid this month yet

    //TODO find customer with highest amount of unpaid rentals
}
