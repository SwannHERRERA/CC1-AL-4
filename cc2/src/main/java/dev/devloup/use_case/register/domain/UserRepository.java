package dev.devloup.use_case.register.domain;

import java.util.List;
import java.util.Optional;

import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;

public interface UserRepository {
  Optional<User> findById(UserId id);

  Optional<User> findByEmail(String email);

  List<User> listAll();

  void add(User user) throws IllegalArgumentException;
}
