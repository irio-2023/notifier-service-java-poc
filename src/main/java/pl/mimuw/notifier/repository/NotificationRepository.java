package pl.mimuw.notifier.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mimuw.notifier.models.NotificationEntity;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<NotificationEntity, UUID> {

}
