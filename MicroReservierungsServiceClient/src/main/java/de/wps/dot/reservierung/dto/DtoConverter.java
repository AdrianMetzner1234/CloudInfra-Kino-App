package de.wps.dot.reservierung.dto;

import java.util.HashMap;
import java.util.Map;

import de.wps.dot.reservierung.Saal;
import de.wps.dot.reservierung.Sitznummer;
import de.wps.dot.reservierung.Sitzplatzbelegung;

public class DtoConverter {
    public static SaalDto saalToSaalDto(Saal saal){
        SaalDto dto = new SaalDto();

        SitznummerDto[][] sitze = new SitznummerDto[saal.getReihen()][saal.getReihenBreite()];

        for (int reihe = 0; reihe < saal.getReihen(); reihe++) {
            for (int sitzNummer = 1; sitzNummer <= saal.getReihenBreite(); sitzNummer++) {
                sitze[reihe][sitzNummer-1] = SitznummerDto.von(reihe, sitzNummer);
            }
        }
        dto.setId(saal.getId());
        dto.setSaalLayout(sitze);
        dto.setName(saal.getName());

        return dto;
    }

    public static Sitzplatzbelegung vonDto(SitzplatzbelegungDto dto) {
        Saal saal = vonDto(dto.getSaal());
        Map<Sitznummer, Boolean> belegung = vonDto(dto.getBelegung());
        Sitzplatzbelegung sitzplatzbelegung = new Sitzplatzbelegung(saal, dto.getVorführungsId(), belegung);
        return sitzplatzbelegung;
    }

    private static Map<Sitznummer, Boolean> vonDto(Map<String, Boolean> belegung) {
        Map<Sitznummer, Boolean> result = new HashMap<>();
        belegung.forEach((key, value) -> result.put(Sitznummer.von(key), value));
        return result;
    }

    private static Saal vonDto(SaalDto saal) {
        return new Saal(saal.getName(), saal.getReihenBreite(), saal.getReihen());
    }

    public static Map<String, Boolean> zuDto(Map<Sitznummer, Boolean> sitzplatzbelegung) {
        Map<String, Boolean> result = new HashMap<>();
        sitzplatzbelegung.forEach((key, value) ->  result.put(key.getName(), value));
        return result;
    }
}
