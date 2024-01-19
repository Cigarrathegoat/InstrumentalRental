package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.TheAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITheAddressRepository extends JpaRepository<TheAddress, Long> {
}
