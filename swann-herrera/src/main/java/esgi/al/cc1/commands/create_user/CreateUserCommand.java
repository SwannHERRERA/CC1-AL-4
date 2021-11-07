package esgi.al.cc1.commands.create_user;

import esgi.al.cc1.domain.Money;

public class CreateUserCommand {
  public final String firstName;
  public final String lastName;
  public final String email;
  public final int age;
  public final Money startBalance;

  public CreateUserCommand(String firstName, String lastName, String email, int age, Money startBalance) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.startBalance = startBalance;
  }
}
