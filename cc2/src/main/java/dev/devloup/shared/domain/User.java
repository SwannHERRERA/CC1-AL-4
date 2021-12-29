package dev.devloup.shared.domain;

import java.util.Objects;

import dev.devloup.core.Entity;

@Entity
public class User {
  private final UserId id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final int age;
  private final Account account;
  private final UserSubscribtion userSubscribtion;

  private User(UserId id, String firstName, String lastName, String email, int age, Account account,
      UserSubscribtion userSubscribtion) {
    this.id = Objects.requireNonNull(id);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
    this.email = Objects.requireNonNull(email.toLowerCase());
    this.age = Objects.requireNonNull(age);
    this.account = Objects.requireNonNull(account);
    this.userSubscribtion = Objects.requireNonNull(userSubscribtion);
  }

  public static User of(UserId id, String firstName, String lastName, String email, int age, Account account,
      UserSubscribtion subscribtion) throws IllegalArgumentException {
    User user = null;
    try {
      user = new User(id, firstName, lastName, email, age, account, subscribtion);
    } catch (Exception e) {
      throw new IllegalArgumentException("error arguement throw null pointer exception");
    }

    var validator = UserValidatorEngine.getInstance();
    if (validator.test(user)) {
      return user;
    }
    throw new IllegalArgumentException(validator.getErrorMessage(user));
  }

  public UserId getId() {
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

  public UserSubscribtion getSubscribtion() {
    return userSubscribtion;
  }

  public UserStatus getStatus() {
    return userSubscribtion.getStatus();
  }
}
