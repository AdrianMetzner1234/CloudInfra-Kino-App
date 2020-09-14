package de.wps.dot.reservierung.konfiguration;

import java.util.Arrays;
import java.util.List;

import de.wps.dot.filmprogramm.FilmprogrammClientProxy;
import de.wps.dot.filmprogramm.Vorführung;
import de.wps.dot.reservierung.Saal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroReservierungsServiceConfiguration {

    @Bean
    public List<Saal> säle() {
        return Arrays.asList(
                new Saal("Großer Saal", 20, 25),
                new Saal("Kleiner Saal", 6, 8)
        );
    }

    @Bean
    public List<Vorführung> vorführungen() {
        List<Vorführung> vorführungen = null;
        while (vorführungen == null) {
            try {
                FilmprogrammClientProxy filmprogrammClient = new FilmprogrammClientProxy();
                vorführungen = filmprogrammClient.getVorführungen();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Kann nicht starten, da Filmprogramm-Service nicht gefunden. Retry in 1 Sekunde.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return vorführungen;
    }
}
