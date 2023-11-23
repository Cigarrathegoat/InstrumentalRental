package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.repository.entities.Rental;

import java.util.List;

public interface IRentalService extends IService<Rental, Exception> {

    public List<Rental> findRentalListByWord(String word) throws RentalNotFoundException;


}
