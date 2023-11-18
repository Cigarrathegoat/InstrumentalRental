package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRentalRepository extends JpaRepository<Rental, String> {

    List<Rental> findRentalByCustomer (Customer customer);

    List<Rental> findRentalByInstrument(Instrument instrument);

    List<Rental> findRentalByAttendant(Attendant attendant);
}
