package dev.devloup.use_case.register.exposition;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.devloup.core.EventBus;
import dev.devloup.core.Listener;
import dev.devloup.core.SimpleEventBus;
import dev.devloup.dummys.DummyEnrollementListener;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;

final class CreateUserCommandHandlerTest {
  CreateUserCommandHandler commandHandler;

  private final String firstname = "Swann";
  private final String lastname = "HERRERA";
  private final String email = "swann@graines-octets.com";
  private final int age = 21;
  private final long startBalance = 0;
  private final EventBus<CreateUserEvent> createUserBus = new SimpleEventBus<CreateUserEvent>();

  CreateUserCommandHandlerTest() {
    commandHandler = new CreateUserCommandHandler(createUserBus, new InMemoryUserRepository());
  }

  @Test
  void test_create_user_with_bad_email() {
    var command = new CreateUserCommand(firstname, lastname, "bad email", age, startBalance);
    Assertions.assertThatThrownBy(() -> commandHandler.createUser(command))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test_create_good_user() {
    var command = new CreateUserCommand(firstname, lastname, email, age, startBalance);
    var createUserEvent = commandHandler.createUser(command);
    var user = createUserEvent.getUser();
    Assertions.assertThat(user.getEmail()).isEqualTo(email);
    Assertions.assertThat(user.getAge()).isEqualTo(age);
    Assertions.assertThat(user.getFirstName()).isEqualTo(firstname);
    Assertions.assertThat(user.getLastName()).isEqualTo(lastname);
  }

  @Test
  void test_create_two_user_with_the_same_email() {
    var command = new CreateUserCommand(firstname, lastname, email, age, startBalance);
    commandHandler.createUser(command);
    Assertions.assertThatThrownBy(() -> commandHandler.createUser(command))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Email already exists");
  }

  @Test
  void test_create_user_publish_in_enrollement_bus() {
    var command = new CreateUserCommand(firstname, lastname, email, age, startBalance);
    Listener<CreateUserEvent> enrollmentListener = Mockito.spy(new DummyEnrollementListener());
    createUserBus.registerListener(enrollmentListener, CreateUserEvent.class);
    var event = commandHandler.createUser(command);

    verify(enrollmentListener, times(1)).accept(event);
  }
}
