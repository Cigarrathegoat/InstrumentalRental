package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.InstrumentNotFoundException;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.interfaces.IInstrumentRepository;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InstrumentService implements IInstrumentService {

    @Autowired
    IInstrumentRepository instrumentRepository;

    @Override
    public Instrument save(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    @Override
    public List<Instrument> findInstrumentByMakeOrModel(String makeOrModel)
            throws InstrumentNotFoundException {
        var instrumentSought = instrumentRepository.findInstrumentByMakeOrModel(makeOrModel);
        if (instrumentSought == null) {
            throw new InstrumentNotFoundException("I01", "Instrument not found");
        } else {
            return instrumentSought;
        }
    }

    @Override
    public List<Instrument> findAll() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument update(Instrument instrument) throws InstrumentNotFoundException {
        var instrumentToUpdate = instrumentRepository.findById(instrument.getInstrumentId())
                .orElseThrow(() -> new InstrumentNotFoundException(
                        "I01", "Instrument not found"
                ));
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
    public void delete(Instrument instrument) throws InstrumentNotFoundException {
        var instrumentToDelete = instrumentRepository.findById(instrument.getInstrumentId())
                .orElseThrow(
                        () -> new InstrumentNotFoundException(
                                "I01", "Instrument not found")
                );
        instrumentRepository.delete(instrumentToDelete);
    }
}
