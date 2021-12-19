package dev.devloup.use_case.register.exposition;

import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserStatus;

public class UserResponse {
  public final String id;
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;
  public final String status;

  public UserResponse(UserId id, String firstName, String lastName, String email, int age, UserStatus status) {
    this.id = id.toString();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.status = status.toString();
  }
}
