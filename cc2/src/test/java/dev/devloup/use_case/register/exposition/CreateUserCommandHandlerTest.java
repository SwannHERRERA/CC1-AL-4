package dev.devloup.use_case.register.exposition;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.devloup.core.ApplicationEvent;
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
  private final double longitude = 0;
  private final double latitude = 0;
  private final double activityRadius = 0;
  private final int dailyRate = 0;
  private final String profession = "BUILDER";
  private final List<String> abilites = Collections.emptyList();

  private final EventBus<ApplicationEvent> bus = new SimpleEventBus<ApplicationEvent>();

  CreateUserCommandHandlerTest() {
    commandHandler = new CreateUserCommandHandler(bus, new InMemoryUserRepository());
  }

  @Test
  void test_create_user_with_bad_email() {
    var command = new CreateUserCommand(firstname, lastname, "bad email", age, startBalance, abilites, profession,
        longitude,
        latitude, activityRadius, dailyRate);
    Assertions.assertThatThrownBy(() -> commandHandler.createUser(command))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test_create_good_user() {
    var command = new CreateUserCommand(firstname, lastname, email, age, startBalance, abilites, profession,
        longitude,
        latitude, activityRadius, dailyRate);
    var createUserEvent = commandHandler.createUser(command);
    var user = createUserEvent.getUser();
    Assertions.assertThat(user.getEmail()).isEqualTo(email);
    Assertions.assertThat(user.getAge()).isEqualTo(age);
    Assertions.assertThat(user.getFirstName()).isEqualTo(firstname);
    Assertions.assertThat(user.getLastName()).isEqualTo(lastname);
  }

  @Test
  void test_create_two_user_with_the_same_email() {
    var command = new CreateUserCommand(firstname, lastname, email, age, startBalance, abilites, profession,
        longitude,
        latitude, activityRadius, dailyRate);
    commandHandler.createUser(command);
    Assertions.assertThatThrownBy(() -> commandHandler.createUser(command))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Email already exists");
  }

  @Test
  void test_create_user_publish_in_enrollement_bus() {
    var command = new CreateUserCommand(firstname, lastname, email, age, startBalance, abilites, profession,
        longitude,
        latitude, activityRadius, dailyRate);
    Listener<CreateUserEvent> enrollmentListener = Mockito.spy(new DummyEnrollementListener());
    bus.registerListener(enrollmentListener, CreateUserEvent.class);
    var event = commandHandler.createUser(command);

    verify(enrollmentListener, times(1)).accept(event);
  }
}
