package dev.devloup.shared.exposition;

import java.util.Objects;

import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserStatus;

public final class UserResponse {
  public final String id;
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;
  public final String status;

  public UserResponse(UserId id, String firstName, String lastName, String email, int age, UserStatus status) {
    this.id = id.toString();
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
    this.email = Objects.requireNonNull(email);
    this.age = Objects.requireNonNull(age);
    this.status = Objects.requireNonNull(status.toString());
  }

  @Override
  public String toString() {
    return "UserResponse [age=" + age + ", email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName="
        + lastName + ", status=" + status + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, age, status);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    UserResponse other = (UserResponse) obj;

    return (id.equals(other.id) && age == other.age && email.equals(other.email)
        && firstName.equals(other.firstName)
        && lastName.equals(other.lastName) && status.equals(other.status));
  }
}
