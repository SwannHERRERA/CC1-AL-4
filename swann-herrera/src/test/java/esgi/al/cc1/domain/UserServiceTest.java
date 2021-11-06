package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  private final UserRepository userRepository = new FakeUserRepository();
  private final String firstName = "Swann";
  private final String lastName = "HERRERA";
  private final String email = "swann@devloup.dev";
  private final int age = 20;
  private final UserService userService;

  UserServiceTest() {
    userService = new UserService(userRepository);
  }

  @Test
  void test_create_user_with_valid_data_is_a_success() {
    boolean response = userService.createUser(new CreateUserCommand(firstName, lastName, email, age));
    assertTrue(response);
  }

  @Test
  void test_create_user_with_valid_data_add_a_user_to_the_repo() {
    userService.createUser(new CreateUserCommand(firstName, lastName, email, age));
    User user = userRepository.findByEmail(email).get();
    assertNotNull(user);
  }

  @Test
  void test_add_two_users_with_the_same_email() {
    userService.createUser(new CreateUserCommand(firstName, lastName, email, age));
    assertThrows(IllegalArgumentException.class, () -> {
      userService.createUser(new CreateUserCommand("different firstname", "different lastname", email, 80));
    });

  }
}
