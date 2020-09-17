package de.wps.dot.reservierung;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gesundheit")
public class Gesundheitsendpunkt {
    private static final Random RANDOM = new Random();

    @GetMapping("/gesund")
    public ResponseEntity GesundheitsEndpunkt() {
       return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/gesund-aber-langsam")
    public ResponseEntity langsamerGesundheitsEndpunkt() throws InterruptedException {
        TimeUnit.MINUTES.sleep(2);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/scheiternd")
    public ResponseEntity scheiternderGesundheitsEndpunkt() {
        return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/häufig-scheiternd")
    public ResponseEntity häufigScheiternderGesundheitsEndpunkt() {
        return getRandomBoolean(0.7f)
                ? new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE)
                : new ResponseEntity(HttpStatus.OK);
    }

    public boolean getRandomBoolean(float p){
        return RANDOM.nextFloat() < p;
    }
}
