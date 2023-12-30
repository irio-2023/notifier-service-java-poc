package pl.mimuw.notifier.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import pl.mimuw.notifier.domain.ServiceAdmin;

import java.util.UUID;

@Value
@Jacksonized
@Builder
public class NotificationRequest {
    UUID id;
    ServiceAdmin primaryAdmin;
    ServiceAdmin secondaryAdmin;
    Integer ackTimeoutSecs;
}
