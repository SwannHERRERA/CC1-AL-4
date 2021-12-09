package dev.devloup.domain;

import java.util.UUID;

import dev.devloup.core.Entity;

@Entity
public class User {
  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final int age;
  private final Account account;
  private UserStatus status;

  public void setStatus(UserStatus status) {
    this.status = status;
  }

  private User(UUID id, String firstName, String lastName, String email, int age, Account account, UserStatus status) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email.toLowerCase();
    this.age = age;
    this.account = account;
    this.status = status;
  }

  public static User of(UUID id, String firstName, String lastName, String email, int age, Account account,
      UserStatus status) throws IllegalArgumentException {
    var user = new User(id, firstName, lastName, email, age, account, status);
    var validator = UserValidatorEngine.getInstance();
    if (validator.test(user)) {
      return user;
    }
    throw new IllegalArgumentException(validator.getErrorMessage(user));
  }

  public void validate() {
    this.status = UserStatus.VERIFIED;
  }

  public void reject() {
    this.status = UserStatus.REJECTED;
  }

  public UUID getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public int getAge() {
    return age;
  }

  public Account getAccount() {
    return account;
  }

  public UserStatus getStatus() {
    return status;
  }
}

