package pl.mimuw.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class NotifierServiceApplication {

    // Swagger UI: http://localhost:8080/swagger-ui.html
    public static void main(String[] args) {
        SpringApplication.run(NotifierServiceApplication.class, args);
    }

}
