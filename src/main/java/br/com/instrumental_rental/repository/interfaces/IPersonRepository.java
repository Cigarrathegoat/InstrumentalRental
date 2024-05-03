package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long> {
}
