package dev.devloup.application.port.in;

public interface CreateUserUseCase {
  CreateUserEvent createUser(CreateUserCommand command);
}

