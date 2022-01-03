package dev.devloup.shared.domain;

import java.time.Period;
import java.time.ZonedDateTime;

import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

public class UserBuilder {
  private final UserId id;
  private String firstname;
  private String lastname;
  private int age;
  private String email;
  private Account account;
  private UserSubscribtion subscrbition;

  private UserBuilder(UserId id) {
    this.id = id;
  }

  public static UserBuilder of(UserId id) {
    return new UserBuilder(id);
  }

  public UserBuilder withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserBuilder withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserBuilder withAge(int age) {
    this.age = age;
    return this;
  }

  public UserBuilder withInitalBalance(Money money) {
    this.account = Account.of(money);
    return this;
  }

  public UserBuilder withInitalBalance(long money) throws NegativeMoneyAmount {
    this.account = Account.of(Money.of(money));
    return this;
  }

  public UserBuilder withSubscribtion(UserSubscribtion subscribtion) {
    this.subscrbition = subscribtion;
    return this;
  }

  public UserBuilder withSubscribtion(UserStatus status, ZonedDateTime lastBilling, Period billingFrequency,
      ZonedDateTime subscribtionDate) {
    this.subscrbition = UserSubscribtion.of(status, lastBilling, billingFrequency, subscribtionDate, id);
    return this;
  }

  public UserBuilder withDefaultSubscrbtion() {
    this.subscrbition = UserSubscribtion.newDefaultSubscribtion(id);
    return this;
  }

  public User build() {
    return User.of(id, firstname, lastname, email, age, account, subscrbition);
  }
}
