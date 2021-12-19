package dev.devloup.use_case.register.exposition;

import dev.devloup.shared.domain.UserId;

public class GetUserByIdQuery {
  private UserId id;

  public GetUserByIdQuery(UserId id) {
    this.id = id;
  }

  public UserId getId() {
    return id;
  }
}
