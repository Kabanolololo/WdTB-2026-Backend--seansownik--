package pl.wsb.seansownik.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.seansownik.rental.entity.Rental;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByCustomer_FullName(String fullName);
}
