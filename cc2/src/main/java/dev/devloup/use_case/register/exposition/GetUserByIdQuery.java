package dev.devloup.use_case.register.exposition;

import java.util.UUID;

import dev.devloup.shared.domain.UserId;

public final class GetUserByIdQuery {
  private UserId id;

  public GetUserByIdQuery(UserId id) {
    this.id = id;
  }

  public UserId getId() {
    return id;
  }

  public static GetUserByIdQuery fromString(String string) {
    return new GetUserByIdQuery(UserId.of(UUID.fromString(string)));
  }
}
