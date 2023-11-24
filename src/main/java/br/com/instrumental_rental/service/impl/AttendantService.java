package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.AttendantNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.interfaces.IAttendantRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AttendantService implements IAttendantService {

    @Autowired
    private IAttendantRepository attendantRepository;

    @Override
    public List<Attendant> findAttendantByNumberProvided(String numberProvided) throws AttendantNotFoundException {
        var attendantSought = attendantRepository.findAttendantByNumberProvided(numberProvided);
        if (attendantSought.isEmpty()) {
            throw new AttendantNotFoundException("A01", "Attendant not found");
        }
        return attendantSought;
    }

    @Override
    public Attendant save(Attendant attendant) {
        return attendantRepository.save(attendant);
    }

    @Override
    public void delete(Attendant attendant) throws AttendantNotFoundException {
        var attendantToDelete = attendantRepository.findById(attendant.getAttendantId())
                .orElseThrow(() -> new AttendantNotFoundException(
                        "A01", "Attendant not found"
                        )
                );
        attendantRepository.delete(attendantToDelete);

    }

    @Override
    public List<Attendant> findAll() {
        return attendantRepository.findAll();
    }

    @Override
    public Attendant update(Attendant attendant) throws AttendantNotFoundException {
        var attendantToUpdate = attendantRepository.findById(attendant.getAttendantId())
                .orElseThrow(() -> new AttendantNotFoundException(
                        "A01", "Attendant not found"
                ));
        attendantToUpdate.setName(attendant.getName());
        attendantRepository.save(attendantToUpdate);
        return attendantToUpdate;
    }
}
