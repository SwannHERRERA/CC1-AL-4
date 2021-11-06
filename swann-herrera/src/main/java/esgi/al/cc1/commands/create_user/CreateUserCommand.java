package esgi.al.cc1.commands.create_user;

public class CreateUserCommand {
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;

  public CreateUserCommand(String firstName, String lastName, String email, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }
}
