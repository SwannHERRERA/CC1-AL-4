package esgi.al.cc2.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import esgi.al.cc2.domain.User;
import esgi.al.cc2.domain.UserRepository;
import esgi.al.cc2.kernel.Repository;

@Repository
public final class MemoryUserRepository implements UserRepository {

  List<User> userList = new ArrayList<>();

  @Override
  public Optional<User> findById(UUID id) {
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

}
