
package de.wps.dot.reservierung.dto;

import java.util.Map;

public class SitzplatzbelegungDto {
	private String vorführungsId;
	private SaalDto saal;

	private final Map<String, Boolean> sitzVerbucht;


	public SitzplatzbelegungDto(SaalDto saal, String vorführungsId, Map<String, Boolean> platzbelegung) {
		this.saal = saal;
		this.vorführungsId = vorführungsId;
		sitzVerbucht = platzbelegung;
	}

	public String getVorführungsId() {
		return vorführungsId;
	}

	public SitzplatzbelegungDto setVorführungsId(String vorführungsId) {
		this.vorführungsId = vorführungsId;
		return this;
	}

	public SaalDto getSaal() {
		return saal;
	}

	public SitzplatzbelegungDto setSaal(SaalDto saal) {
		this.saal = saal;
		return this;
	}

	public Map<String, Boolean> getBelegung() {
		return sitzVerbucht;
	}
}
