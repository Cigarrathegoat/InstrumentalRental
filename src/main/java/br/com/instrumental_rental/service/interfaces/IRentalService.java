package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.*;
import br.com.instrumental_rental.repository.entities.Rental;

import java.util.List;

public interface IRentalService extends IService<Rental, Throwable> {

    public Rental save(Rental rental) throws WithdrawalGreaterThanBalanceException,
            EndDateNotAfterStartDateException, CustomerNotFoundException, InstrumentNotFoundException,
            AttendantNotFoundException;

    public void delete(Rental rental) throws RentalNotFoundException;

    public List<Rental> findAll();

    public Rental update(Rental rental) throws RentalNotFoundException;

    public List<Rental> findRentalListByWord(String word) throws RentalNotFoundException;


}
