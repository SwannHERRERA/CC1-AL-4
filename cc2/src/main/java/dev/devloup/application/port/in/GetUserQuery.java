package dev.devloup.application.port.in;

import dev.devloup.domain.UserId;

public class GetUserQuery {
  private UserId id;
  public GetUserQuery(UserId id) {
    this.id = id;
  }
  public UserId getId() {
    return id;
  }
}
