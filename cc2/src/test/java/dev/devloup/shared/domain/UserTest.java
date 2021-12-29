package dev.devloup.shared.domain;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dev.devloup.core.SimpleEventBus;
import dev.devloup.use_case.register.exposition.PaymentEvent;

final class UserTest {
  private final UserId id = UserId.of(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"));
  private final String firstName = "Swann";
  private final String lastName = "HERRERA";
  private final String email = "swann@devloup.dev";
  private final int age = 20;
  private final UserSubscribtion subscribtion = UserSubscribtion.newDefaultSubscribtion();
  private final Account account = Account.of(Money.ZERO, new SimpleEventBus<PaymentEvent>());

  @Test
  void test_creation_of_user() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion);
    assertEquals(id, user.getId());
    assertEquals(firstName, user.getFirstName());
    assertEquals(lastName, user.getLastName());
    assertEquals(email, user.getEmail());
    assertEquals(age, user.getAge());
    assertEquals(UserStatus.CURRENTLY_AUDITED, user.getStatus());
  }

  @Test
  void test_creation_of_user_with_non_lowercase_email_should_convert_it_to_lowercase() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion);
    assertEquals(email.toLowerCase(), user.getEmail());
  }

  @Test
  void test_user_creation_with_non_valid_email() {
    String email = "random string";
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion);
    });
  }

  @Test
  void test_user_creation_with_non_valid_name() {
    String lastName = null;
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion);
    });
  }

  @Test
  void test_user_creation_with_non_valid_field() {
    String lastName = null;
    UserId id = null;
    String firstName = null;
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion);
    });
  }

  @Test
  void test_error_message_for_userCreation_with_bad_email() {
    String email = "random string";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion);
    });
    String message = exception.getMessage();
    assertTrue(message.contains("invalid email"));
  }

  @Test
  void test_error_message_for_userCreation_with_bad_field() {
    String email = "random string";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion);
    });
    String message = exception.getMessage();
    assertTrue(message.contains("invalid email"));
  }

  @Test
  void test_user_minor() {
    int age = 0;
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion);
    });
    String message = exception.getMessage();
    assertTrue(message.contains("user should be major"));
  }

  @Test
  void test_validation_engine_when_user_is_correct() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion);
    UserValidatorEngine engine = UserValidatorEngine.getInstance();
    assertNull(engine.getErrorMessage(user));
  }

  @Test
  void test_rejection_user() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion);
    assertEquals(UserStatus.CURRENTLY_AUDITED, user.getStatus());
    user.getSubscribtion().reject();
    assertEquals(UserStatus.REJECTED, user.getStatus());
  }

  @Test
  void test_validation_user() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion);
    assertEquals(UserStatus.CURRENTLY_AUDITED, user.getStatus());
    user.getSubscribtion().validate();
    assertEquals(UserStatus.VERIFIED, user.getStatus());
  }
}
