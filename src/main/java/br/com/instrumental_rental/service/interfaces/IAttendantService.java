package br.com.instrumental_rental.service.interfaces;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;

import java.util.List;

public interface IAttendantService {

    public Attendant save(Attendant attendant);

    public void delete(Attendant attendant) throws AttendantNotFoundException;

    public List<Attendant> findAll();

    public Attendant update(Attendant attendant) throws AttendantNotFoundException;

    List<Attendant> findAttendantByName(String name) throws AttendantNotFoundException;
}
