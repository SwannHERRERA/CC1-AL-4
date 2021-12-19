package dev.devloup.use_case.register.exposition;

public class UserRequest {
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;

  public UserRequest(String firstName, String lastName, String email, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }
}
