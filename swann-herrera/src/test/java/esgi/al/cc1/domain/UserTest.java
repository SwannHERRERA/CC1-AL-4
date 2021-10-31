package esgi.al.cc1.domain;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

public class UserTest {
  // TODO Create test for non lowercase email
  @Test
  public void testCreationOfUser() {
    UUID uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    String firstName = "Swann";
    String lastName = "HERRERA";
    String email = "swann@devloup.dev";
    int age = 20;
    User user = User.of(uuid, firstName, lastName, email, age);
    assertEquals(uuid, user.getId());
    assertEquals(firstName, user.getFirstName());
    assertEquals(lastName, user.getLastName());
    assertEquals(email, user.getEmail());
    assertEquals(age, user.getAge());
  }
}
