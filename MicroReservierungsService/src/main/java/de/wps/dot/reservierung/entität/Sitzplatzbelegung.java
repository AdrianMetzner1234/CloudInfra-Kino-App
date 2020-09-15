
package de.wps.dot.reservierung.entität;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import de.wps.dot.reservierung.Saal;

@Entity
public class Sitzplatzbelegung {
	@Id
	private String id;
	private String vorfuehrungsId;
	private String saalId;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<Sitznummer, Boolean> sitzVerbucht;
	
	public Sitzplatzbelegung(String id, Saal saal, String vorfuehrungsId) {
		this.id = id;
		this.vorfuehrungsId = vorfuehrungsId;
		this.sitzVerbucht = new HashMap<>();
		this.saalId = saal.getId();

		saal.getSitze().forEach(sitz -> sitzVerbucht.put(Sitznummer.von(sitz.getSitzReihe(), sitz.getStuhlnummer()), false));
	}

	protected Sitzplatzbelegung() {

	}

	public String getSaalId() {
		return saalId;
	}

	public String getVorfuehrungsId() {
		return vorfuehrungsId;
	}

	public void setVorfuehrungsId(String vorführungsId) {
		this.vorfuehrungsId = vorführungsId;
	}

	public boolean isSitzFrei(de.wps.dot.reservierung.Sitznummer sitznummer) {
		return !sitzVerbucht.get(sitznummer);
	}
	
	public Set<Sitznummer> getFreieSitze() {
		Set<Sitznummer> result = new HashSet<>();
		for(Sitznummer sitz : sitzVerbucht.keySet()) {
			if(!sitzVerbucht.get(sitz)) {
				result.add(sitz);
			}
		}
		return result;
	}

	public Map<String, Boolean> getSitzVerbuchung() {
		Map<String, Boolean> result = new HashMap<>();
		sitzVerbucht.forEach((key, value) -> result.put(key.getName(), value));
		return result;
	}

	public synchronized boolean bucheSitze(List<Sitznummer> sitze) {
		if (getFreieSitze().containsAll(sitze)) {
			sitze.forEach(sitz -> sitzVerbucht.replace(sitz, true));
			return true;
		}

		return false;
	}

}
