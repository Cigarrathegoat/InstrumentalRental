package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;

import java.util.List;

public interface IAttendantService {

    List<Attendant> saveFirstTime (List<Attendant> attendantList) throws StoreNotFoundException, AttendantNotFoundException;

    Attendant save(Attendant attendant);

    void delete(Long attendantId) throws AttendantNotFoundException;

    List<Attendant> findAll();

    Attendant update(Attendant attendant) throws AttendantNotFoundException;

    Attendant findAttendantByNumberProvided(String numberProvided) throws AttendantNotFoundException;

    Attendant findAttendantById(Long id) throws AttendantNotFoundException;

    void addToRentals(Long attendantId, Long rentalId) throws AttendantNotFoundException, RentalNotFoundException;

    void addToStore(Long attendantId, Long storeId) throws AttendantNotFoundException, StoreNotFoundException;


}
