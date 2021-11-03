package esgi.al.cc1.domain;

import java.util.UUID;

public interface UserRepository {
  User findById(UUID id);

  User findByEmail(String email);

  void add(User user);
}
