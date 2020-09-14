package de.wps.dot.reservierung;

import de.wps.dot.reservierung.entität.Reservierung;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReservierungsRepository extends CrudRepository<Reservierung, String> {
    @Query("SELECT coalesce(max(r.reservierungsnummer), 1) FROM Reservierung r")
    int findeMaximaleReservierungsnummer();
}
