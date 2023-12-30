package pl.mimuw.notifier.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mimuw.notifier.domain.ServiceAdmin;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document("notifications")
public class NotificationEntity {

    @Id
    private UUID id;

    private ServiceAdmin primaryAdmin;

    private ServiceAdmin secondaryAdmin;

    private Integer ackTimeoutSecs;
}

