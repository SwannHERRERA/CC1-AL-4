package dev.devloup.shared.infrastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserStatus;
import dev.devloup.use_case.register.domain.UserRepository;

public final class InMemoryUserRepository implements UserRepository {
  List<User> userList = new ArrayList<>();

  @Override
  public Optional<User> findById(UserId id) {
    return userList.stream().filter(u -> u.getId().equals(id)).findFirst();
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userList.stream().filter(u -> u.getEmail().equals(email)).findFirst();
  }

  @Override
  public void add(User user) throws IllegalArgumentException {
    if (findByEmail(user.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Email already exists");
    }
    userList.add(user);
  }

  @Override
  public List<User> listAll() {
    return Collections.unmodifiableList(userList);
  }

  @Override
  public List<User> listAllActive() {
    return userList.stream().filter(user -> user.getSubscribtion().getStatus() == UserStatus.VERIFIED).toList();
  }
}
