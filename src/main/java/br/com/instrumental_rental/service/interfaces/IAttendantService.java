package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;

import java.util.List;

public interface IAttendantService extends IService<Attendant, Exception> {

    List<Attendant> findAttendantByName(String name) throws AttendantNotFoundException;
}
