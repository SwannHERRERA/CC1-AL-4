package esgi.al.cc1.domain;

import java.util.UUID;

public class UserId {
  private final UUID id;

  private UserId(UUID id) {
    this.id = id;
  }

  public static UserId generateId() {
    return new UserId(UUID.randomUUID());
  }

  public UUID getId() {
    return id;
  }
}
