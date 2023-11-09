package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttendantRepository extends JpaRepository<Attendant, String> {

    List<Attendant> findAttendantByName(String name);
}
