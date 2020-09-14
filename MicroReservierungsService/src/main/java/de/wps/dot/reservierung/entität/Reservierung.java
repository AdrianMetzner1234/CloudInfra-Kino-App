package de.wps.dot.reservierung.entität;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservierung {

	protected Reservierung() {

	}

	public static Reservierung erzeugeReservierung(String vorführungskennung, List<Sitznummer> sitznummern, int reservierungsNummer) {
		return new Reservierung(vorführungskennung, sitznummern, reservierungsNummer);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ElementCollection
	@CollectionTable(name ="Sitznummern")
	private List<Sitznummer> sitznummern;
	private String vorführungkennung;
	private int reservierungsnummer;

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
