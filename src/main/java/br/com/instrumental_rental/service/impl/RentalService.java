package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.repository.entities.Rental;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RentalService implements IRentalService {

    IRentalRepository rentalRepositoryAttribute;

    @Autowired
    private RentalService (IRentalRepository rentalRepositoryParameter) {
        this.rentalRepositoryAttribute = rentalRepositoryParameter;
    }
    @Override
    public Rental save(Rental rental) {
        return null;
    }

    @Override
    public void delete(Rental rental) throws Exception {

    }

    @Override
    public List<Rental> findAll() {
        return null;
    }

    @Override
    public Rental update(Rental rental) throws Exception {
        return null;
    }
}
