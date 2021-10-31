package esgi.al.cc1.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
  // TODO Create test for non lowercase email
  @Test
  public void testCreationOfUser() {
    // Le UUID
    String firstName = "Swann";
    String lastName = "HERRERA";
    String email = "swann@devloup.dev";
    int age = 20;
    User user = User.of(firstName, lastName, email, age);
    assertEquals(firstName, user.getFirstName());
    assertEquals(lastName, user.getLastName());
    assertEquals(email, user.getEmail());
    assertEquals(age, user.getAge());
  }
}
