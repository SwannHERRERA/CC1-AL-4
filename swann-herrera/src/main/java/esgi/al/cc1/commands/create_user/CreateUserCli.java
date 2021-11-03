package esgi.al.cc1.commands.create_user;

import esgi.al.cc1.domain.UserService;

public class CreateUserCli implements CreateUserUseCase {
  final UserService userService;

  public CreateUserCli(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean createUser(CreateUserCommand command) {
    // TODO Auto-generated method stub
    return false;
  }

}
