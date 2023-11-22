package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.RentalNotFoundException;
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

    private Rental finder(String rentalId) throws RentalNotFoundException {
        return   rentalRepositoryAttribute.findById(rentalId)
                .orElseThrow(() -> new RentalNotFoundException("R01", "Rental not found"));
    }

    @Override
    public Rental save(Rental rental) {

        return rentalRepositoryAttribute.save(rental);
    }

    @Override
    public void delete(Rental rental) throws RentalNotFoundException {
        var rentalToDelete = finder(rental.getRentalId());
        rentalRepositoryAttribute.delete(rentalToDelete);
    }

    @Override
    public List<Rental> findAll() {
        return rentalRepositoryAttribute.findAll());
    }

    @Override
    public Rental update(Rental rental) throws Exception {
        return null;
    }
}
