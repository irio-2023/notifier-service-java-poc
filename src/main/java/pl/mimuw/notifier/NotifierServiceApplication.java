package pl.mimuw.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotifierServiceApplication {

    // Swagger UI: http://localhost:8080/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(NotifierServiceApplication.class, args);
    }

}
