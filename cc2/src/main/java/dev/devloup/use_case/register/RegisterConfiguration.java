package dev.devloup.use_case.register;

import javax.enterprise.context.ApplicationScoped;

import dev.devloup.core.EventBus;
import dev.devloup.core.SimpleEventBus;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.register.application.UserMapper;
import dev.devloup.use_case.register.domain.UserRepository;
import dev.devloup.use_case.register.exposition.CreateUserCommandHandler;
import dev.devloup.use_case.register.exposition.CreateUserEvent;
import dev.devloup.use_case.register.exposition.ListUserQueryHandler;
import dev.devloup.use_case.register.exposition.PaymentEvent;

@ApplicationScoped
public class RegisterConfiguration {
  @ApplicationScoped
  public ListUserQueryHandler listAllUserQueryHandler() {
    return new ListUserQueryHandler(userMapper(), inMemoryRepository());
  }

  @ApplicationScoped
  public CreateUserCommandHandler createUserCommandHandler() {
    return new CreateUserCommandHandler(eventBusCreateUserEvent(), eventBusPayment(), inMemoryRepository());
  }

  @ApplicationScoped
  public EventBus<CreateUserEvent> eventBusCreateUserEvent() {
    return new SimpleEventBus<>();
  }

  @ApplicationScoped
  public EventBus<PaymentEvent> eventBusPayment() {
    return new SimpleEventBus<>();
  }

  @ApplicationScoped
  public UserRepository inMemoryRepository() {
    return new InMemoryUserRepository();
  }

  @ApplicationScoped
  public UserMapper userMapper() {
    return new UserMapper();
  }
}