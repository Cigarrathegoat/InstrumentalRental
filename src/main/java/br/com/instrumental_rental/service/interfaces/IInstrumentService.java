package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.repository.entities.Instrument;

import java.util.List;

public interface IInstrumentService extends IService<Instrument, Exception> {

    List<Instrument> findInstrumentByModel(String model) throws InstrumentNotFoundException;
}
