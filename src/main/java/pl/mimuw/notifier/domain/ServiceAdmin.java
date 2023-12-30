package pl.mimuw.notifier.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceAdmin {
    String email;
}
