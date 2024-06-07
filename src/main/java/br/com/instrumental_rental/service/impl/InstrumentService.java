package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.interfaces.IInstrumentRepository;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InstrumentService implements IInstrumentService {

    @Autowired
    IInstrumentRepository instrumentRepository;

    @Override
    public List<Instrument> saveFirstTime(List<Instrument> instrumentList)
            throws StoreNotFoundException, InstrumentNotFoundException {
        List<Instrument> savedInstruments = new ArrayList<>();
        for (Instrument instrument : instrumentList) {
            savedInstruments.add(instrumentRepository.save(instrument));
        }
        return savedInstruments;
    }

    @Override
    public Instrument save(Instrument instrument) throws StoreNotFoundException, InstrumentNotFoundException {
        return instrumentRepository.save(instrument);
    }

    @Override
    public List<Instrument> findInstrumentByMakeOrModel(String makeOrModel)
            throws InstrumentNotFoundException {
        var instrumentSought = instrumentRepository.findInstrumentByMakeOrModel(makeOrModel);
        if (instrumentSought.isEmpty()) {
            throw new InstrumentNotFoundException("I01", "Instrument not found");
        } else {
            return instrumentSought;
        }
    }

    @Override
    public List<Instrument> listAll() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument findById(Long instrumentId) throws InstrumentNotFoundException {
        return instrumentRepository.findById(instrumentId)
                .orElseThrow(() -> new InstrumentNotFoundException(
                        "I01", "Instrument not found"
                ));
    }

    @Override
    public Instrument update(Instrument instrument) throws InstrumentNotFoundException,
            StoreNotFoundException {
        var instrumentToUpdate = findById(instrument.getInstrumentId());
        instrumentToUpdate.setType(instrument.getType());
        instrumentToUpdate.setMake(instrument.getMake());
        instrumentToUpdate.setModel(instrument.getModel());
        instrumentToUpdate.setPrice(instrument.getPrice());
        instrumentToUpdate.setManufactureDate(instrument.getManufactureDate());
        instrumentToUpdate.setAvailable(instrument.isAvailable());
        save(instrumentToUpdate);
        return instrumentToUpdate;
    }

    @Override
    public void delete(Long instrumentId) throws InstrumentNotFoundException {
        var instrumentToDelete = instrumentRepository.findById(instrumentId)
                .orElseThrow(
                        () -> new InstrumentNotFoundException(
                                "I01", "Instrument not found")
                );
        instrumentRepository.delete(instrumentToDelete);
    }
}
