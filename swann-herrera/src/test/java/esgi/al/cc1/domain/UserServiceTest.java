package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import esgi.al.cc1.domain.commands.create_user.CreateUserCommand;
import esgi.al.cc1.infrastructure.EnrollmentListener;
import esgi.al.cc1.infrastructure.MemoryUserRepository;
import esgi.al.cc1.infrastructure.NullLogger;

class UserServiceTest {
  private final UserRepository userRepository = new MemoryUserRepository();
  private final EventBus<PaymentEvent> paymentBus = new EventBus<>();
  private final EventBus<CreateUserEvent> enrollmentBus = new EventBus<>();
  private final String firstName = "Swann";
  private final String lastName = "HERRERA";
  private final String email = "swann@devloup.dev";
  private final int age = 20;
  private final UserService userService;

  UserServiceTest() {
    userService = new UserService(userRepository, enrollmentBus, paymentBus);
  }

  @Test
  void test_create_user_with_valid_data_is_a_success() {
    CreateUserEvent event = userService.createUser(new CreateUserCommand(firstName, lastName, email, age, Money.ZERO));
    assertEquals(email, event.getUser().getEmail());
  }

  @Test
  void test_create_user_with_valid_data_add_a_user_to_the_repo() {
    userService.createUser(new CreateUserCommand(firstName, lastName, email, age, Money.ZERO));
    var user = userRepository.findByEmail(email).get();
    assertNotNull(user);
  }

  @Test
  void test_add_two_users_with_the_same_email() {
    CreateUserCommand command1 = new CreateUserCommand(firstName, lastName, email, age, Money.ZERO);
    userService.createUser(command1);
    CreateUserCommand command2 = new CreateUserCommand("different firstname", "different lastname", email, 80,
        Money.ZERO);
    Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.createUser(command2));
    String message = exception.getMessage();
    assertTrue(message.contains("Email already exists"));
  }

  @Test
  void test_user_creation_call_enrollement_listener_but_user_doent_have_enought_money() {
    Logger logger = new NullLogger();
    Listener<CreateUserEvent> listener = Mockito
        .spy(new EnrollmentListener(Account.of(Money.ZERO, paymentBus), logger));
    enrollmentBus.registerListener(listener);
    CreateUserEvent event = userService.createUser(new CreateUserCommand(firstName, lastName, email, age, Money.ZERO));
    verify(listener, times(1)).accept(event);
    assertEquals(UserStatus.REJECTED, event.getUser().getStatus());
  }

  @Test
  void test_user_creation_call_enrollement_listener() {
    Logger logger = new NullLogger();
    Listener<CreateUserEvent> listener = Mockito
        .spy(new EnrollmentListener(Account.of(Money.ZERO, paymentBus), logger));
    enrollmentBus.registerListener(listener);
    CreateUserEvent event = userService
        .createUser(new CreateUserCommand(firstName, lastName, email, age, Money.of(500)));
    verify(listener, times(1)).accept(event);
    assertEquals(UserStatus.VERIFIED, event.getUser().getStatus());
  }
}
