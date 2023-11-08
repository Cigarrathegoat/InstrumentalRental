package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IInstrumentRepository extends JpaRepository<Instrument, String> {

    List<Instrument> findInstrumentByModel(String model);
}
