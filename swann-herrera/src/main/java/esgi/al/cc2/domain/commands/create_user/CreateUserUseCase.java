package esgi.al.cc2.domain.commands.create_user;

import esgi.al.cc2.domain.CreateUserEvent;

public interface CreateUserUseCase {
  CreateUserEvent createUser(CreateUserCommand command);
}
