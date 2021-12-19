package dev.devloup.use_case.register.exposition;

public interface CreateUserUseCase {
  CreateUserEvent createUser(CreateUserCommand command);
}
