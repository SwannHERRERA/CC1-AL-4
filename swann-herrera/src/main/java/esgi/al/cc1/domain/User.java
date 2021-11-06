package esgi.al.cc1.domain;

import java.util.UUID;

import esgi.al.cc1.kernel.Entity;

@Entity
public class User {
  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final int age;

  private User(UUID id, String firstName, String lastName, String email, int age) {

    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email.toLowerCase();
    this.age = age;
  }

  public static User of(UUID id, String firstName, String lastName, String email, int age) {
    User user = new User(id, firstName, lastName, email, age);
    UserValidatorEngine validationEngine = UserValidatorEngine.getInstance();
    if (validationEngine.test(user)) {
      return user;
    }
    throw new IllegalArgumentException(validationEngine.getErrorMessage(user));
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

}
