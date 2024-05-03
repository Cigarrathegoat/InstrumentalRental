package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Rental;
import br.com.instrumental_rental.repository.interfaces.IRentalRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static br.com.instrumental_rental.service.util.ServiceUtil.sufficientBalanceChecker;

@Service
@Slf4j
public class RentalService implements IRentalService {

    IRentalRepository rentalRepositoryAttribute;

    ICustomerService customerServiceAttribute;

    IInstrumentService instrumentServiceAttribute;

    IAttendantService attendantServiceAttribute;

    @Autowired
    private RentalService(IRentalRepository rentalRepositoryParameter,
                          ICustomerService customerServiceParameter,
                          IInstrumentService instrumentServiceParameter,
                          IAttendantService attendantServiceParameter) {
        this.rentalRepositoryAttribute = rentalRepositoryParameter;
        this.customerServiceAttribute = customerServiceParameter;
        this.instrumentServiceAttribute = instrumentServiceParameter;
        this.attendantServiceAttribute = attendantServiceParameter;
    }
    /*TODO search other classes for finders and turn them into findById*/
    public Rental findById(Long rentalId) throws RentalNotFoundException {
        return rentalRepositoryAttribute.findById(rentalId)
                .orElseThrow(() -> new RentalNotFoundException("R01", "Rental not found"));
    }

    private void rentalDatesChecker(Rental rental) throws EndDateNotAfterStartDateException {

        if (!rental.getStartDate().isBefore(rental.getEndDate())) {
            throw new EndDateNotAfterStartDateException(
                    "E01", "the end date must be at least one day after the start date"
            );
        }
    }

    private void emptyListChecker(List<Rental> rentalListSought) throws RentalNotFoundException {
        if (rentalListSought.isEmpty()) {
            throw new RentalNotFoundException("R01", "no rentals found");
        }
    }

    private BigDecimal rentalPriceSetter(Instrument instrument, Rental rental) {
        rental.setPrice((instrument.getPrice().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))
                .multiply(BigDecimal.valueOf(
                                ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate())
                        )
                )
        );
        return rental.getPrice();
    }

    private BigDecimal attendantCommissionSetter(Rental rental) {
        return rental.getPrice().divide(BigDecimal.valueOf(10), RoundingMode.HALF_UP);
    }

    private void nonRentalAttributesUpdater(Instrument instrument, Customer customer,
                                            Attendant attendant, Rental rental)
            throws StoreNotFoundException, InstrumentNotFoundException,
            CustomerNotFoundException, AttendantNotFoundException {
        instrument.setAvailable(!instrument.isAvailable());
        customer.setAccountBalance(customer.getAccountBalance().subtract(
                        rentalPriceSetter(instrument, rental)));
        attendant.setTotalCommission(attendant.getTotalCommission().add(
                attendantCommissionSetter(rental)));
        instrumentServiceAttribute.save(instrument);
        customerServiceAttribute.save(customer);
        attendantServiceAttribute.save(attendant);
    }

    /*public void findInstrument{
        List<Instrument> instruments = // Call your findInstrument method
        if (!instruments.isEmpty()) {
            selectedInstrument = instruments.get(0);
        } else {
            // Handle the case where no instruments are found
        }*/
/*TODO create a new Rental object, make it ony have ID*/
    @Override
    public List<Rental> saveFirstTime(List<Rental> rentalList) throws CustomerNotFoundException,
            InstrumentNotFoundException, RentalNotFoundException,
            AttendantNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, StoreNotFoundException {
        List<Rental> savedRentals = new ArrayList<>();
        for (Rental rental : rentalList) {
            rental.setCustomer(customerServiceAttribute.findCustomerById(rental.getCustomer()
                    .getPersonId()));
            customerServiceAttribute.addToRentals(rental.getCustomer().getPersonId(), rental.getRentalId());
            rental.setInstrument(instrumentServiceAttribute.findById(rental.getInstrument().getInstrumentId()));
            instrumentServiceAttribute.addToRentals(rental.getInstrument().getInstrumentId(), rental.getRentalId());
            rental.setAttendant(attendantServiceAttribute.findAttendantById(rental.getAttendant().getPersonId()));
            attendantServiceAttribute.addToRentals(rental.getAttendant().getPersonId(), rental.getRentalId());
            nonRentalAttributesUpdater(rental.getInstrument(), rental.getCustomer(),
                    rental.getAttendant(), rental);
            rentalDatesChecker(rental);
            sufficientBalanceChecker(rental.getCustomer(), rental.getPrice());

            Rental savedRental = rentalRepositoryAttribute.save(rental);
            savedRentals.add(savedRental);
        }
        return savedRentals;
    }

    @Override
    public Rental save(Rental rental) throws CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, RentalNotFoundException, WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, StoreNotFoundException {

        rental.setCustomer(customerServiceAttribute.findCustomerById(rental.getCustomer()
                .getPersonId()));
        customerServiceAttribute.addToRentals(rental.getCustomer().getPersonId(), rental.getRentalId());
        rental.setInstrument(instrumentServiceAttribute.findById(rental.getInstrument().getInstrumentId()));
        instrumentServiceAttribute.addToRentals(rental.getInstrument().getInstrumentId(), rental.getRentalId());
        rental.setAttendant(attendantServiceAttribute.findAttendantById(rental.getAttendant().getPersonId()));
        attendantServiceAttribute.addToRentals(rental.getAttendant().getPersonId(), rental.getRentalId());
            nonRentalAttributesUpdater(rental.getInstrument(), rental.getCustomer(),
                    rental.getAttendant(), rental);
            rentalDatesChecker(rental);
            sufficientBalanceChecker(rental.getCustomer(), rental.getPrice());


            rentalRepositoryAttribute.save(rental);
        return rental;
    }

    @Override
    public void delete(Long rentalId) throws RentalNotFoundException {
        var rentalToDelete = findById(rentalId);
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
        var rentalToUpdate = findById(rental.getRentalId());
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
