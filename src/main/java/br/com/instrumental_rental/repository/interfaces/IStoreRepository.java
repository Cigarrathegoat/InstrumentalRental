package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IStoreRepository extends JpaRepository<Store, Long> {

    @Query(value = "SELECT s FROM Store s WHERE s.name = :name")
    Store findStoreByName(@Param("name")String name);
}
