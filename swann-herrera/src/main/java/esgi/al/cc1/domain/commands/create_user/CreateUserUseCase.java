package esgi.al.cc1.domain.commands.create_user;

import esgi.al.cc1.domain.CreateUserEvent;

public interface CreateUserUseCase {
  CreateUserEvent createUser(CreateUserCommand command);
}
