package dev.devloup.exposition;

import javax.enterprise.context.ApplicationScoped;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.Config;
import dev.devloup.core.EventBus;
import dev.devloup.core.Logger;
import dev.devloup.core.LoggerFactory;
import dev.devloup.core.SimpleEventBus;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.addMoney.exposition.AddMoneyCommandHandler;
import dev.devloup.use_case.register.application.UserMapper;
import dev.devloup.use_case.register.domain.UserRepository;
import dev.devloup.use_case.register.exposition.CreateUserCommandHandler;
import dev.devloup.use_case.register.exposition.ListUserQueryHandler;
import dev.devloup.use_case.transaction.exposition.RecurentTransactionHandler;

@ApplicationScoped
public class ApplicationConfiguration {

  @ApplicationScoped
  public Account applicationAccount() {
    return Account.of(Money.ZERO);
  }

  @ApplicationScoped
  public ListUserQueryHandler listAllUserQueryHandler() {
    return new ListUserQueryHandler(userMapper(), inMemoryRepository());
  }

  @ApplicationScoped
  public CreateUserCommandHandler createUserCommandHandler() {
    return new CreateUserCommandHandler(eventBus(), inMemoryRepository());
  }

  @ApplicationScoped
  public EventBus<ApplicationEvent> eventBus() {
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

  @ApplicationScoped
  public AddMoneyCommandHandler addMoneyCommandHandler() {
    return new AddMoneyCommandHandler(inMemoryRepository(), userMapper());
  }

  @ApplicationScoped
  public RecurentTransactionHandler recurentTransactionHandler() {
    return new RecurentTransactionHandler(inMemoryRepository(), applicationAccount(), eventBus());
  }

  @ApplicationScoped
  public Logger logger() {
    return LoggerFactory.createFileLogger(Config.getLogFolder());
  }
}