package dev.devloup.use_case.register.exposition;

import dev.devloup.shared.domain.Money;

public class CreateUserCommand {
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;
  public final long startBalance;

  public CreateUserCommand(String firstName, String lastName, String email, int age, Money startBalance) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.startBalance = startBalance.toPrimitive();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CreateUser: {" + System.lineSeparator());
    sb.append("firstName: " + firstName + System.lineSeparator());
    sb.append("lastName: " + lastName + System.lineSeparator());
    sb.append("email: " + email + System.lineSeparator());
    sb.append("age: " + age + System.lineSeparator());
    sb.append("startBalance: " + startBalance + System.lineSeparator());
    sb.append("}" + System.lineSeparator());

    return sb.toString();
  }
}
