package esgi.al.cc1.domain;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UserTest {
  private UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
  private String firstName = "Swann";
  private String lastName = "HERRERA";
  private String email = "swann@devloup.dev";
  private int age = 20;

  @Test
  void test_creation_of_user() {

    User user = User.of(uuid, firstName, lastName, email, age);
    assertEquals(uuid, user.getId());
    assertEquals(firstName, user.getFirstName());
    assertEquals(lastName, user.getLastName());
    assertEquals(email, user.getEmail());
    assertEquals(age, user.getAge());
  }

  @Test
  void test_creation_of_user_with_non_lowercase_email_should_convert_it_to_lowercase() {
    User user = User.of(uuid, firstName, lastName, email, age);
    assertEquals(email.toLowerCase(), user.getEmail());
  }

  @Test
  void test_user_creation_with_non_valid_email() {
    String email = "random string";
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(uuid, firstName, lastName, email, age);
    });
  }

  @Test
  void test_user_creation_with_non_valid_name() {
    String lastName = null;
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(uuid, firstName, lastName, email, age);
    });
  }

  @Test
  void test_user_creation_with_non_valid_field() {
    String lastName = null;
    UUID uuid = null;
    String firstName = null;
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(uuid, firstName, lastName, email, age);
    });
  }
}
