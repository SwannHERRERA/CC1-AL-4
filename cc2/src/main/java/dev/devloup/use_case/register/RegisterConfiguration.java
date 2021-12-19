package dev.devloup.use_case.register;

import javax.enterprise.context.ApplicationScoped;

import dev.devloup.use_case.register.exposition.CreateUserCommandHandler;
import dev.devloup.use_case.register.exposition.ListAllUserQueryHandler;

@ApplicationScoped
public class RegisterConfiguration {
  @ApplicationScoped
  public ListAllUserQueryHandler listAllUserQueryHandler() {
    return new ListAllUserQueryHandler();
  }

  @ApplicationScoped
  public CreateUserCommandHandler createUserCommandHandler() {
    return new CreateUserCommandHandler();
  }
}
