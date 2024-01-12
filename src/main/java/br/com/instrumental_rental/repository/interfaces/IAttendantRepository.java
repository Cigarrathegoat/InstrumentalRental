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
    Attendant findAttendantByNumberProvided(String numberProvided);

    @Query(value = "SELECT A.attendantId, COUNT(R.rental_id) AS total_rentals" +
            "FROM Rental R JOIN Attendant A ON R.attendantId = A.attendantId" +
            "WHERE MONTH(R.rental_date) = <1> GROUP BY A.attendantId" +
            "ORDER BY total_rentals DESC" +
            "LIMIT 1")
    Attendant findAttendantOfTheMonth();
    //TODO run query to find which attendant has made most sales, per month
}
