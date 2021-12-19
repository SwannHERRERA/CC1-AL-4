package dev.devloup.application.port.in;

import dev.devloup.domain.User;
import dev.devloup.domain.UserId;
import dev.devloup.domain.UserStatus;

public class UserDTO {
  public final UserId id;
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;
  public final UserStatus status;
  private UserDTO(UserId id, String firstName, String lastName, String email, int age, UserStatus status) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.status = status;
  }

  public static UserDTO of(User user) {
    return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getStatus());
  }
}
