package de.wps.dot.reservierung;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Reservierung {

	// Factory vergibt eindeutige Reservierungsnummer
	private final List<Sitznummer> sitznummern;
	private final String vorführungkennung;
	private final int reservierungsnummer;

	public Reservierung(String vorführungskennung, List<Sitznummer> sitznummern, int reservierungsnummer) {
		this.vorführungkennung = vorführungskennung;
		this.sitznummern = sitznummern;
		this.reservierungsnummer = reservierungsnummer;
	}

	public Collection<Sitznummer> getSitznummern() {
		return Collections.unmodifiableCollection(sitznummern);
	}

	public String getVorführungsnummer() {
		return vorführungkennung;
	}

	public int getReservierungsnummer() {
		return reservierungsnummer;
	}
}
