package esgi.al.cc1.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import esgi.al.cc1.commands.create_user.CreateUserCommand;

public class UserServiceTest {
  @Test
  public void test_create_user_with_valid_data() {
    UserService userService = new UserService();
    boolean response = userService.createUser(new CreateUserCommand("Swann", "HERRERA", "swann@devloup.dev", 20));
    assertTrue(response);
  }
}
