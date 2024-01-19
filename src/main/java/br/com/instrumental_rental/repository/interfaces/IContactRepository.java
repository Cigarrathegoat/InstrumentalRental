package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact, Long> {
}
