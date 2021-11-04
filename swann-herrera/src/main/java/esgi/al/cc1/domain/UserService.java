package esgi.al.cc1.domain;

import esgi.al.cc1.commands.create_user.CreateUserCommand;
import esgi.al.cc1.commands.create_user.CreateUserUseCase;

public class UserService implements CreateUserUseCase {
  @Override
  public boolean createUser(CreateUserCommand command) {
    // TODO implements
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
