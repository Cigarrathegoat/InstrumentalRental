package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.repository.entities.Rental;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RentalService implements IRentalService {

    IRentalRepository rentalRepositoryAttribute;

    ICustomerService customerServiceAttribute;

    IInstrumentService instrumentServiceAttribute;

    IAttendantService attendantServiceAttribute;

    @Autowired
    private RentalService (IRentalRepository rentalRepositoryParameter,
                           ICustomerService customerServiceParameter,
                           IInstrumentService instrumentServiceParameter,
                           IAttendantService attendantServiceParameter) {
        this.rentalRepositoryAttribute = rentalRepositoryParameter;
        this.customerServiceAttribute = customerServiceParameter;
        this.instrumentServiceAttribute = instrumentServiceParameter;
        this.attendantServiceAttribute = attendantServiceParameter;
    }

    private Rental idFinder(String rentalId) throws RentalNotFoundException {
        return   rentalRepositoryAttribute.findById(rentalId)
                .orElseThrow(() -> new RentalNotFoundException("R01", "Rental not found"));
    }
    private void emptyListChecker(List<Rental> rentalListSought) throws RentalNotFoundException {
        if (rentalListSought.isEmpty()) {
            throw new RentalNotFoundException("R01", "no rentals found");
        }
    }

    @Override
    public Rental save(Rental rental) {
        return rentalRepositoryAttribute.save(rental);
    }

    @Override
    public void delete(Rental rental) throws RentalNotFoundException {
        var rentalToDelete = idFinder(rental.getRentalId());
        rentalRepositoryAttribute.delete(rentalToDelete);
    }

    @Override
    public List<Rental> findRentalListByWord(String word) throws RentalNotFoundException {
        var rentalToFind = rentalRepositoryAttribute.findRentalByWord(word);
        emptyListChecker(rentalToFind);
        return rentalToFind;
    }

    @Override
    public List<Rental> findAll() {
        return rentalRepositoryAttribute.findAll();
    }

    @Override
    public Rental update(Rental rental) throws RentalNotFoundException {
        var rentalToUpdate = idFinder(rental.getRentalId());
        rentalToUpdate.setCustomer(rental.getCustomer());
        rentalToUpdate.setAttendant(rental.getAttendant());
        rentalToUpdate.setInstrument(rental.getInstrument());
        rentalToUpdate.setPrice(rental.getPrice());
        rentalToUpdate.setStartDate(rental.getStartDate());
        rentalToUpdate.setEndDate(rental.getEndDate());

        rentalRepositoryAttribute.save(rentalToUpdate);
        return rentalToUpdate;
    }
}
