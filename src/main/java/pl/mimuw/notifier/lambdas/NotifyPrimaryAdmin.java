package pl.mimuw.notifier.lambdas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import pl.mimuw.notifier.dto.NotificationRequest;
import pl.mimuw.notifier.models.NotificationEntity;
import pl.mimuw.notifier.repository.NotificationRepository;

import java.io.BufferedReader;
import java.util.function.Function;

@Slf4j
@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotifyPrimaryAdmin {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    @Bean
    public Function<Message<BufferedReader>, String> function() {
        return request -> {
            final String jsonBody = getJsonBody(request);
            try {
                final NotificationRequest notificationRequest = getNotificationRequest(jsonBody);
                log.info("Received notification request: {}", notificationRequest);
                final NotificationEntity notificationEntity = mapNotificationDtoToEntity(notificationRequest);
                notificationRepository.save(notificationEntity);
                log.info("Successfully saved notification to database");
            } catch (JsonProcessingException e) {
                log.error("Error while parsing request: {}", jsonBody, e);
                throw new RuntimeException(e);
            }
            return "OK";
        };
    }

    private NotificationEntity mapNotificationDtoToEntity(NotificationRequest notificationRequest) {
        final NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setId(notificationRequest.getId());
        notificationEntity.setPrimaryAdmin(notificationRequest.getPrimaryAdmin());
        notificationEntity.setSecondaryAdmin(notificationRequest.getSecondaryAdmin());
        notificationEntity.setAckTimeoutSecs(notificationRequest.getAckTimeoutSecs());
        return notificationEntity;
    }

    private NotificationRequest getNotificationRequest(String jsonBody) throws JsonProcessingException {
        return objectMapper.readValue(jsonBody, NotificationRequest.class);
    }

    private String getJsonBody(Message<BufferedReader> request) {
        return request.getPayload().lines().reduce("", String::concat).replaceAll("\\s+", " ");
    }
}
