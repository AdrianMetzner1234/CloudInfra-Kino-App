
package de.wps.dot.reservierung;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sitzplatzbelegung {
	private String vorführungsId;
	private final Saal saal;
	private final Map<Sitznummer, Boolean> sitzVerbucht;

	public Sitzplatzbelegung(Saal saal, String vorführungsId, Map<Sitznummer, Boolean> sitzVerbucht) {
		this.saal = saal;
		this.vorführungsId = vorführungsId;
		this.sitzVerbucht = sitzVerbucht;
	}
	
	public Sitzplatzbelegung(Saal saal, String vorführungsId) {
		this.saal = saal;
		this.vorführungsId = vorführungsId;
		sitzVerbucht = new HashMap<>();

		saal.getSitze().forEach(sitz -> sitzVerbucht.put(sitz, false));
	}
	
	public Saal getSaal() {
		return saal;
	}

	public String getVorführungsId() {
		return vorführungsId;
	}

	public void setVorführungsId(String vorführungsId) {
		this.vorführungsId = vorführungsId;
	}

	public boolean isSitzFrei(Sitznummer sitznummer) {
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

	public synchronized boolean bucheSitze(List<Sitznummer> sitze) {
		if (getFreieSitze().containsAll(sitze)) {
			sitze.forEach(sitz -> sitzVerbucht.replace(sitz, true));
			return true;
		}

		return false;
	}

}
