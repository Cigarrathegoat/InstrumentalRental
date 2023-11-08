package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalRepository extends JpaRepository<Rental, String> {
}
