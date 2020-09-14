package de.wps.dot.reservierung.konfiguration;


import de.wps.dot.reservierung.ausnahme.InvaliderInhaltstypAusnahme;
import de.wps.dot.reservierung.ausnahme.NichtGefundenAusnahme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class KinoKontrollerAnweisung {
    @ExceptionHandler(NichtGefundenAusnahme.class)
    public ResponseEntity notFoundException(final NichtGefundenAusnahme e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvaliderInhaltstypAusnahme.class)
    public ResponseEntity notFoundException(final InvaliderInhaltstypAusnahme e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
