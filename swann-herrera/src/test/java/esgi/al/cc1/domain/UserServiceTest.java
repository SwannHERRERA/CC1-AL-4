package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import esgi.al.cc1.Config;
import esgi.al.cc1.commands.create_user.CreateUserCommand;

class UserServiceTest {
  private final UserRepository userRepository = new FakeUserRepository();
  private final EventBus<CreateUserEvent> eventBus = new EventBus<>();
  private final EventBus<PaymentEvent> uselessBus = new EventBus<>();
  private final String firstName = "Swann";
  private final String lastName = "HERRERA";
  private final String email = "swann@devloup.dev";
  private final int age = 20;
  private final UserService userService;

  UserServiceTest() {
    userService = new UserService(userRepository, eventBus, uselessBus);
  }

  @Test
  void test_create_user_with_valid_data_is_a_success() {
    boolean response = userService
        .createUser(new CreateUserCommand(firstName, lastName, email, age, Money.of(Config.BASE_MONEY)));
    assertTrue(response);
  }

  @Test
  void test_create_user_with_valid_data_add_a_user_to_the_repo() {
    userService.createUser(new CreateUserCommand(firstName, lastName, email, age, Money.of(Config.BASE_MONEY)));
    User user = userRepository.findByEmail(email).get();
    assertNotNull(user);
  }

  @Test
  void test_add_two_users_with_the_same_email() {
    CreateUserCommand command1 = new CreateUserCommand(firstName, lastName, email, age, Money.of(Config.BASE_MONEY));
    userService.createUser(command1);
    CreateUserCommand command2 = new CreateUserCommand("different firstname", "different lastname", email, 80,
        Money.of(Config.BASE_MONEY));
    Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(command2));
    String message = exception.getMessage();
    assertTrue(message.contains("Email already exists"));

  }
}
