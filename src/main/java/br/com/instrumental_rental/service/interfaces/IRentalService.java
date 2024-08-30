package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.repository.entities.Rental;

import java.util.List;

public interface IRentalService {

    Rental save(Rental rental,Long customerId) throws WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException, RentalNotFoundException, StoreNotFoundException;

    public void delete(Long rentalId) throws RentalNotFoundException;

    public List<Rental> findAll();

    public Rental update(Rental rental) throws RentalNotFoundException;

    public List<Rental> findRentalListByWord(String word) throws RentalNotFoundException;

    public Rental findById(Long rentalId) throws RentalNotFoundException;


}
