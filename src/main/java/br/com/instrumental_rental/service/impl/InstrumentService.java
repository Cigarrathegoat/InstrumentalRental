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
        return null;
    }
    @Override
    public List<Instrument> findInstrumentByModel(String model) throws InstrumentNotFoundException {
        var instrumentSought = instrumentRepository.findInstrumentByModel(model);
        if (instrumentSought == null) {
            throw new InstrumentNotFoundException("I01", "Instrument not found");
        } else {
            return instrumentSought;
        }
    }

    @Override
    public List<Instrument> findAll() {
        return null;
    }

    @Override
    public Instrument update(Instrument instrument) throws InstrumentNotFoundException {
        return null;
    }



    @Override
    public void delete(Instrument instrument) throws InstrumentNotFoundException {

    }
}
