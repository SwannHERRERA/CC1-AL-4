package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import esgi.al.cc1.commands.create_user.CreateUserCommand;

class FakeUserRepository implements UserRepository {
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
  public void add(User user) {
    userList.add(user);
  }

}

class UserServiceTest {
  UserRepository userRepository = new FakeUserRepository();

  @Test
  void test_create_user_with_valid_data_is_a_success() {
    UserService userService = new UserService(userRepository);
    boolean response = userService.createUser(new CreateUserCommand("Swann", "HERRERA", "swann@devloup.dev", 20));
    assertTrue(response);
  }

  @Test
  void test_create_user_with_valid_data_add_a_user_to_the_repo() {
    String userEmail = "swann@devloup.dev";
    UserService userService = new UserService(userRepository);

    userService.createUser(new CreateUserCommand("Swann", "HERRERA", userEmail, 20));
    User user = userRepository.findByEmail(userEmail).get();
    assertNotNull(user);
  }
}
