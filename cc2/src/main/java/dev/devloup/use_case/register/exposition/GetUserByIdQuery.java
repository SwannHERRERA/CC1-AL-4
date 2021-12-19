package dev.devloup.application.port.in;

import dev.devloup.domain.UserId;

public class GetUserByIdQuery {
  private UserId id;

  public GetUserByIdQuery(UserId id) {
    this.id = id;
  }

  public UserId getId() {
    return id;
  }
}
