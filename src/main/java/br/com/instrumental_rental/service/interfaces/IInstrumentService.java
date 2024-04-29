package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Instrument;

import java.util.List;

public interface IInstrumentService {

    public List<Instrument> saveFirstTime(List<Instrument> instrumentList);

    Instrument save(Instrument instrument) throws StoreNotFoundException, InstrumentNotFoundException;

    public List<Instrument> listAll();

    Instrument findById(Long instrumentId) throws InstrumentNotFoundException;

    public Instrument update(Instrument instrument) throws InstrumentNotFoundException;

    public void delete(Long instrumentId) throws InstrumentNotFoundException;


    List<Instrument> findInstrumentByMakeOrModel(String makeOrModel) throws InstrumentNotFoundException;

    void addToRentals(Long instrumentId, Long rentalId) throws InstrumentNotFoundException, RentalNotFoundException;

    void addToStore(Long instrumentId, Long StoreId) throws InstrumentNotFoundException, StoreNotFoundException;
}
