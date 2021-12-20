package dev.devloup.use_case.register;

import javax.enterprise.context.ApplicationScoped;

import dev.devloup.core.EventBus;
import dev.devloup.core.SimpleEventBus;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.register.domain.UserRepository;
import dev.devloup.use_case.register.exposition.CreateUserCommandHandler;
import dev.devloup.use_case.register.exposition.CreateUserEvent;
import dev.devloup.use_case.register.exposition.ListAllUserQueryHandler;
import dev.devloup.use_case.register.exposition.PaymentEvent;

@ApplicationScoped
public class RegisterConfiguration {
  @ApplicationScoped
  public ListAllUserQueryHandler listAllUserQueryHandler() {
    return new ListAllUserQueryHandler();
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
}