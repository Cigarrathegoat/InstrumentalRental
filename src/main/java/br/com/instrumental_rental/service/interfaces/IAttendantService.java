package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;

import java.util.List;

public interface IAttendantService {

    Attendant save(Attendant attendant);

    void delete(Long attendantId) throws AttendantNotFoundException;

    List<Attendant> findAll();

    Attendant update(Attendant attendant) throws AttendantNotFoundException;

    Attendant findAttendantByNumberProvided(String numberProvided) throws AttendantNotFoundException;

    Attendant findAttendantById(Long id) throws AttendantNotFoundException;


}
