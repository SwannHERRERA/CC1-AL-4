package esgi.al.cc1.commands.create_user;

public class CreateUserCommand {
  final String firstName;
  final String lastName;
  final String email;
  final int age;

  public CreateUserCommand(String firstName, String lastName, String email, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
  }
}
