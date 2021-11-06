package esgi.al.cc1.domain;

import java.util.Optional;
import java.util.UUID;

import esgi.al.cc1.kernel.Repository;

@Repository
public interface UserRepository {
  Optional<User> findById(UUID id);

  Optional<User> findByEmail(String email);

  void add(User user);
}
