package pl.mimuw.notifier.domain;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class ServiceAdmin {
    String email;
}
