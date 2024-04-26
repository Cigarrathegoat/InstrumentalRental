package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<Store, Long> {
}
