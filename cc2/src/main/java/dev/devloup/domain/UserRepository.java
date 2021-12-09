package dev.devloup.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<User> findById(UUID id);

  Optional<User> findByEmail(String email);

  void add(User user) throws IllegalArgumentException;
}
