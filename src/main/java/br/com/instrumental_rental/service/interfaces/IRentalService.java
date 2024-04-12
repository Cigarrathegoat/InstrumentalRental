package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.repository.entities.Rental;

import java.util.List;

public interface IRentalService {

    public List<Rental> save(List<Rental> rentalList) throws WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException;

    public void delete(Long rentalId) throws RentalNotFoundException;

    public List<Rental> findAll();

    public Rental update(Rental rental) throws RentalNotFoundException;

    public List<Rental> findRentalListByWord(String word) throws RentalNotFoundException;


}
