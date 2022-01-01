package dev.devloup.shared.infrastructure;

import java.time.LocalDate;
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
  public List<User> listAllNonActiveSubscribtion() {
    var today = LocalDate.now();
    return userList.stream().filter(user -> {
      var subscribtion = user.getSubscribtion();
      if (subscribtion.getStatus() != UserStatus.VERIFIED) {
        return false;
      }
      var nextBillingDate = subscribtion.getLastBilling().plus(subscribtion.getBillingFrequency()).toLocalDate();
      return nextBillingDate.isEqual(today);
    }).toList();
  }
}
