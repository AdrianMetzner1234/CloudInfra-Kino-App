package de.wps.dot.reservierung;
import de.wps.dot.reservierung.entität.Sitzplatzbelegung;
import org.springframework.data.repository.CrudRepository;

public interface SaalplanRepository extends CrudRepository<Sitzplatzbelegung, String> {
    Sitzplatzbelegung findByVorführungsId(String vorführungsId);
}
