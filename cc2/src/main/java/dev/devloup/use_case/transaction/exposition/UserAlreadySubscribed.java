package dev.devloup.use_case.transaction.exposition;

import dev.devloup.shared.domain.User;

public final class UserAlreadySubscribed extends RuntimeException {
  public UserAlreadySubscribed(User user) {
    super("user: " + user + " already exist");
  }
}
