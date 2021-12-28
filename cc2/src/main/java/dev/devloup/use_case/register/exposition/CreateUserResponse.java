package dev.devloup.use_case.register.exposition;

import java.util.UUID;

import dev.devloup.shared.domain.UserId;

public class CreateUserResponse {
  public final String userId;
  public final String eventId;

  public CreateUserResponse(UserId userId, UUID eventId) {
    this.userId = userId.toString();
    this.eventId = eventId.toString();
  }
}
