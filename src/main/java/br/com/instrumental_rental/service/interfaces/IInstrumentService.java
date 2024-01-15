package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.repository.entities.Instrument;

import java.util.List;

public interface IInstrumentService {

    public Instrument save(Instrument instrument);

    public List<Instrument> listAll();

    Instrument findById(Instrument instrument) throws InstrumentNotFoundException;

    public Instrument update(Instrument instrument) throws InstrumentNotFoundException;

    public void delete(Long instrumentId) throws InstrumentNotFoundException;


    List<Instrument> findInstrumentByMakeOrModel(String makeOrModel) throws InstrumentNotFoundException;
}
