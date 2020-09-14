package de.wps.dot.reservierung.ausnahme;

public class NichtGefundenAusnahme extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NichtGefundenAusnahme() {
		super();
	}

	public NichtGefundenAusnahme(String message) {
		super(message);
	}
}
