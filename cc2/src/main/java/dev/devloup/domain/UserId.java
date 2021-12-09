package dev.devloup.domain;

import java.util.Objects;
import java.util.UUID;

public class UserId {
  private final UUID id;

  private UserId(UUID id) {
    this.id = id;
  }

  public static UserId of(UUID eternalId) {
    var id = Objects.requireNonNull(eternalId);
    return new UserId(id);
  }
  public static UserId generate() {
    return new UserId(UUID.randomUUID());
  }

  public UUID getId() {
    return id;
  }
}
