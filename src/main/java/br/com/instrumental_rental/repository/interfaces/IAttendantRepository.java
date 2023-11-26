package br.com.instrumental_rental.repository.interfaces;

import br.com.instrumental_rental.repository.entities.Attendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAttendantRepository extends JpaRepository<Attendant, Long> {
/*@Query(value = "SELECT c from Customer c" +
            "WHERE c.socialSecurityNumber = :numberProvided OR c.driversLicenseNumber = :numberProvided")*/

    @Query(value = "SELECT a FROM Attendant a" +
    "WHERE a.socialSecurityNumber = :numberProvided OR a.driversLicenseNumber = :numberProvided")
    List<Attendant> findAttendantByNumberProvided(String numberProvided);
}
