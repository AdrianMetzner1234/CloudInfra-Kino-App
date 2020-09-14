package de.wps.dot.reservierung.ausnahme;

public class InvaliderInhaltstypAusnahme extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvaliderInhaltstypAusnahme() {
        super();
    }

    public InvaliderInhaltstypAusnahme(String message) {
        super(message);
    }
}
