package co.com.sofka.api.healthchaeck;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/")
public class HealthCheckController{

    @GetMapping
    public ResponseEntity<String> getclientActive() {
        return ResponseEntity.status(HttpStatus.OK).body("is ok");
    }

}