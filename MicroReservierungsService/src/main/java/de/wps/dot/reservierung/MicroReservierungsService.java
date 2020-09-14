package de.wps.dot.reservierung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackageClasses = {WebReservierungsService.class, ReservierungsService.class})
@EnableJpaRepositories
public class MicroReservierungsService {
    public static void main(String[] args) {
        SpringApplication.run(MicroReservierungsService.class, args);
    }
}
