package dev.devloup.shared.domain;

import java.util.Objects;
import java.util.UUID;

public class TransactionId {
  private final UUID id;

  private TransactionId(UUID id) {
    this.id = id;
  }

  public static TransactionId of(UUID eternalId) {
    var id = Objects.requireNonNull(eternalId);
    return new TransactionId(id);
  }

  public static TransactionId generate() {
    return new TransactionId(UUID.randomUUID());
  }

  public UUID getId() {
    return id;
  }

  public String toString() {
    return id.toString();
  }

}
