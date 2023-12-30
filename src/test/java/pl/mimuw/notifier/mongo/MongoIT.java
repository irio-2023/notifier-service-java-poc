package pl.mimuw.notifier.mongo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mimuw.notifier.AbstractIT;
import pl.mimuw.notifier.domain.ServiceAdmin;
import pl.mimuw.notifier.models.NotificationEntity;
import pl.mimuw.notifier.repository.NotificationRepository;

import java.util.UUID;

public class MongoIT extends AbstractIT {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void checkDatabaseConnection() {
        // given
        final var notificationEntity = new NotificationEntity();
        notificationEntity.setId(UUID.randomUUID());
        notificationEntity.setAckTimeoutSecs(60);
        notificationEntity.setPrimaryAdmin(ServiceAdmin.builder()
                .email("mail1@example.com")
                .build());
        notificationEntity.setSecondaryAdmin(ServiceAdmin.builder()
                .email("mail2@example.com")
                .build());

        // when
        notificationRepository.save(notificationEntity);

        // then
        final var response = notificationRepository.findById(notificationEntity.getId());
        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(notificationEntity.getAckTimeoutSecs(), response.get().getAckTimeoutSecs());
        Assertions.assertEquals(notificationEntity.getPrimaryAdmin().getEmail(), response.get().getPrimaryAdmin().getEmail());
        Assertions.assertEquals(notificationEntity.getSecondaryAdmin().getEmail(), response.get().getSecondaryAdmin().getEmail());
    }
}
