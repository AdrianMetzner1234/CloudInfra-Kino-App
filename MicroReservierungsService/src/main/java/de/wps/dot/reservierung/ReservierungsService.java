package de.wps.dot.reservierung;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import de.wps.dot.filmprogramm.Vorführung;
import de.wps.dot.reservierung.entität.Reservierung;
import de.wps.dot.reservierung.entität.Sitznummer;
import de.wps.dot.reservierung.entität.Sitzplatzbelegung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservierungsService {
	private final List<Saal> säle;
	private final ReservierungsRepository reservierungsRepository;
	private final SaalplanRepository saalplanRepository;

	@Autowired
	public ReservierungsService(List<Saal> säle,
								List<Vorführung> vorführungen,
								ReservierungsRepository reservierungsRepository,
								SaalplanRepository saalplanRepository) {
		this.säle = säle;
		this.reservierungsRepository = reservierungsRepository;
		this.saalplanRepository = saalplanRepository;

		for (Vorführung vorführung : vorführungen) {
			Optional<Saal> optionalSaal = findeSaal(säle, vorführung.getSaalId());
			if (optionalSaal.isEmpty())
				continue;
			Saal saal = optionalSaal.get();
			saalplanRepository.save(new Sitzplatzbelegung(vorführung.getId()+"--"+saal.getId(), saal, vorführung.getId()));
		}
	}

	private Optional<Saal> findeSaal(List<Saal> säle, String saalId) {
		return säle.stream().filter(saal -> saal.getId().equals(saalId)).findFirst();
	}

	public Set<Sitznummer> getFreieSitze(String vorstellungsid) {
		assert isVorstellungBekannt(vorstellungsid);

		Sitzplatzbelegung sitzplatzbelegung = saalplanRepository.findByVorführungsId(vorstellungsid);

		return sitzplatzbelegung.getFreieSitze();
	}

	public Sitzplatzbelegung getSitzplatzbelegung(String vorstellungsid) {
		assert isVorstellungBekannt(vorstellungsid);

		return saalplanRepository.findByVorführungsId(vorstellungsid);
	}

	public boolean isVorstellungBekannt(String vorstellungsid) {
		return (saalplanRepository.findByVorführungsId(vorstellungsid) != null);
	}

	public Reservierung bucheSitze(String vorstellungsid, List<Sitznummer> requestedSeats) {
		assert isVorstellungBekannt(vorstellungsid);

		Sitzplatzbelegung sitzplatzbelegung = saalplanRepository.findByVorführungsId(vorstellungsid);
		if (sitzplatzbelegung.bucheSitze(requestedSeats)) {
			int maximaleReservierungsnummer = reservierungsRepository.findeMaximaleReservierungsnummer();
			Reservierung reservierung = Reservierung.erzeugeReservierung(vorstellungsid, requestedSeats, ++maximaleReservierungsnummer);
			reservierungsRepository.save(reservierung);
			return reservierung;
		}
		return null;
	}

	public List<Saal> getSäle() {
		return säle;
	}
}
