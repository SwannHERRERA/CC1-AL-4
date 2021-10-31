package esgi.al.cc1.domain;

import esgi.al.cc1.kernel.Entity;

@Entity
public class User {
  private final UserId id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final int age;

  private User(UserId id, String firstName, String lastName, String email, int age) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }

  public static User of(String firstName, String lastName, String email, int age) {
    // TODO find best option between this and dependency injection
    UserId id = UserId.generateId();
    return new User(id, firstName, lastName, email, age);
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

}
