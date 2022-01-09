package dev.devloup.use_case.register.exposition;

import java.util.List;
import java.util.Objects;

public final class CreateUserCommand {
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;
  public final long startBalance;
  public final List<String> abilities;
  public final String profession;
  public final double longitude;
  public final double latitude;
  public final double activityRadius;
  public final int dailyRate;

  public CreateUserCommand(String firstName, String lastName, String email, int age, long startBalance,
      List<String> abilities, String profession, double longitude, double latitude, double activityRadius,
      int dailyRate) {
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
    this.email = Objects.requireNonNull(email);
    this.age = age;
    this.startBalance = startBalance;
    this.abilities = Objects.requireNonNull(abilities);
    this.profession = Objects.requireNonNull(profession);
    this.longitude = longitude;
    this.latitude = latitude;
    this.activityRadius = activityRadius;
    this.dailyRate = dailyRate;
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
