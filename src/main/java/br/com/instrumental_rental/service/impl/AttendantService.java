package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.exceptions.RentalNotFoundException;
import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.interfaces.IAttendantRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.IRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AttendantService implements IAttendantService {

    @Autowired
    private IAttendantRepository attendantRepository;

    @Autowired
    private IRentalService rentalService;



    @Override
    public Attendant findAttendantByNumberProvided(String numberProvided) throws AttendantNotFoundException {
        var attendantSought = attendantRepository.findAttendantByNumberProvided(numberProvided);
        if (attendantSought == null) {
            throw new AttendantNotFoundException("A01", "Attendant not found");
        }
        return attendantSought;
    }

    @Override
    public Attendant findAttendantById(Long id) throws AttendantNotFoundException {
        return attendantRepository.findById(id).orElseThrow(
                () -> new AttendantNotFoundException("A01", "Attendant not found"));
    }

    @Override
    public void addToRentals(Long attendantId, Long rentalId) throws AttendantNotFoundException,
            RentalNotFoundException {
        findAttendantById(attendantId).getRentals().add(rentalService.findById(rentalId));
    }

    @Override
    public void addToStore(Long attendantId, Long storeId) throws AttendantNotFoundException, StoreNotFoundException {
        findAttendantById(attendantId).setStore();
    }


    @Override
    public List<Attendant> saveFirstTime(List<Attendant> attendantList) {
        List<Attendant> savedAttendants = new ArrayList<>();
        for (Attendant attendant : attendantList) {
            Attendant savedAttendant = attendantRepository.save(attendant);
            savedAttendants.add(savedAttendant);
        }
        return savedAttendants;
    }

    @Override
    public Attendant save(Attendant attendant) {
        return attendantRepository.save(attendant);
    }

    @Override
    public void delete(Long attendantId) throws AttendantNotFoundException {

        attendantRepository.delete(findAttendantById(attendantId));

    }

    @Override
    public List<Attendant> findAll() {
        return attendantRepository.findAll();
    }

    @Override
    public Attendant update(Attendant attendant) throws AttendantNotFoundException {
        var attendantToUpdate = findAttendantById(attendant.getPersonId());
        attendantToUpdate.setName(attendant.getName());
        attendantToUpdate.setContacts(attendant.getContacts());
        attendantToUpdate.setDateOfBirth(attendant.getDateOfBirth());
        attendantToUpdate.setSocialSecurityNumber(attendant.getSocialSecurityNumber());
        attendantToUpdate.setDriversLicenseNumber(attendant.getDriversLicenseNumber());
        attendantRepository.save(attendantToUpdate);
        return attendantToUpdate;
    }
}
