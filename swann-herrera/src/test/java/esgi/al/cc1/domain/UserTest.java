package esgi.al.cc1.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {
  // TODO Create test for non lowercase email
  @Test
  void testCreationOfUser() {
    // Le UUID
    String firstName = "Swann";
    String lastName = "HERRERA";
    String email = "swann@devloup.dev";
    int age = 20;
    User user = User.of(firstName, lastName, email, age);
    assertTrue(firstName.equals(user.getFirstName()));
    assertTrue(lastName.equals(user.getLastName()));
    assertTrue(email.equals(user.getEmail()));
    assertTrue(age == user.getAge());
  }
}
