package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

public interface IInstrumentRepository extends JpaRepository<Instrument, Long> {

    @Query(value = "SELECT i FROM Instrument i WHERE i.make = :makeOrModel OR i.model = :makeOrModel")
    List<Instrument> findInstrumentByMakeOrModel(@Param("makeOrModel")String makeOrModel);
    //TODO: write query to find IDs of all instruments of a certain type

   /* @Query(value = "SELECT I.instrumentId FROM Rental R JOIN Instrument I ON R.instrumentId = I.instrumentId")
    List<Instrument> listAllInstrumentsThatHaveBeenRented(); */
    //TODO: write query to find IDs of all instruments that have been rented


}
