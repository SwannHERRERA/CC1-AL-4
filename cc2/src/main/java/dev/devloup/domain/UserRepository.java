package dev.devloup.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  Optional<User> findById(UserId id);

  Optional<User> findByEmail(String email);

  List<User> listAll();

  void add(User user) throws IllegalArgumentException;
}
