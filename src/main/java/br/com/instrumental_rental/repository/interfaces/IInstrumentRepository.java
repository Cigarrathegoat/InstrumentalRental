package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

public interface IInstrumentRepository extends JpaRepository<Instrument, Long> {

    @Query(value = "SELECT i FROM Instrument i WHERE i.make = :makeOrModel OR i.model = :makeOrModel")
    List<Instrument> findInstrumentByMakeOrModel(String makeOrModel);
    //TODO: write query to find IDs of all instruments of a certain type
    //TODO: write query to find IDs of all instruments that have been rented
    @Query("")

}
