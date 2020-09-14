package de.wps.dot.reservierung;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.wps.dot.reservierung.ausnahme.NichtGefundenAusnahme;
import de.wps.dot.reservierung.dto.DtoConverter;
import de.wps.dot.reservierung.dto.SaalDto;
import de.wps.dot.reservierung.dto.SitzplatzbelegungDto;
import de.wps.dot.reservierung.entität.Reservierung;
import de.wps.dot.reservierung.entität.Sitznummer;
import de.wps.dot.reservierung.entität.Sitzplatzbelegung;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebReservierungsService {
    private final ReservierungsService reservierungsService;

    public WebReservierungsService(ReservierungsService reservierungsService) {
        this.reservierungsService = reservierungsService;
    }

    @GetMapping(value = "free_seats")
    public Object freeSeats(@RequestParam String vorstellungsId) {
        if (!reservierungsService.isVorstellungBekannt(vorstellungsId)) {
            throw new NichtGefundenAusnahme();
        }

        return reservierungsService.getFreieSitze(vorstellungsId);
    }

    @PostMapping(value = "/booking/{vorstellungsId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Reservierung> bookSeats(@PathVariable("vorstellungsId") String vorstellungsId,
                                                  @RequestBody MultiValueMap<String, String> body) {
        List<Sitznummer> result = new ArrayList<>();
        for (String seat : body.get("sitze")) {
            //TODO: macht die Annahme das es nur einstellige Reihenbezeichnungen gibt.
            result.add(Sitznummer.von(seat.substring(0, 1), Integer.parseInt(seat.substring(1))));
        }

        return bookSeatsInternal(vorstellungsId, result);
    }

    @PostMapping(value = "/booking/{vorstellungsId}", consumes = "application/json")
    public ResponseEntity<Reservierung> bookSeats(@PathVariable("vorstellungsId") String vorstellungsId,
                                                  @RequestBody String[] body) {
        List<Sitznummer> requestedSeats = new ArrayList<>();
        for (String seat : body) {
            //TODO: macht die Annahme das es nur einstellige Reihenbezeichnungen gibt.
            requestedSeats.add(Sitznummer.von(seat.substring(0, 1), Integer.parseInt(seat.substring(1))));
        }
        return bookSeatsInternal(vorstellungsId, requestedSeats);
    }

    @GetMapping(value = "/säle")
    public List<Saal> säle() {
        return reservierungsService.getSäle();
    }

    @GetMapping(value = "/säle/")
    public Saal saalMitId(@RequestParam String saalId) {
        return reservierungsService.getSäle().stream()
                .filter(it -> it.getId().equals(saalId))
                .findAny()
                .orElseThrow(NichtGefundenAusnahme::new);
    }

    @GetMapping(value = "/sitzplatzbelegung/{vorstellungsId}")
    public ResponseEntity<SitzplatzbelegungDto> sitzplatzbelegung(@PathVariable("vorstellungsId") String vorstellungsId) throws JsonProcessingException {
        Sitzplatzbelegung platzbelegung = reservierungsService.getSitzplatzbelegung(vorstellungsId);
        SaalDto saal = DtoConverter.saalToSaalDto(saalMitId(platzbelegung.getSaalId()));
        SitzplatzbelegungDto result = new SitzplatzbelegungDto(saal, vorstellungsId, reservierungsService.getSitzplatzbelegung(vorstellungsId).getSitzVerbuchung());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private ResponseEntity<Reservierung> bookSeatsInternal(String vorstellungsId, List<Sitznummer> requestedSeats) {
        if (!reservierungsService.isVorstellungBekannt(vorstellungsId)) {
            throw new NichtGefundenAusnahme();
        }


        Reservierung reservierung = reservierungsService.bucheSitze(vorstellungsId, requestedSeats);
        if (reservierung == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(reservierung, HttpStatus.CREATED);
    }


}
