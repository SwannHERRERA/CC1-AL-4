package dev.devloup.use_case.register.application;

import java.util.Objects;

import dev.devloup.core.ApplicationEventListener;
import dev.devloup.core.Logger;
import dev.devloup.use_case.register.exposition.CreateUserEvent;
import dev.devloup.use_case.transaction.exposition.RecurentTransactionHandler;

public final class EnrollmentListener implements ApplicationEventListener<CreateUserEvent> {
  private final Logger logger;
  private final RecurentTransactionHandler transactionHandler;

  public EnrollmentListener(Logger logger, RecurentTransactionHandler transactionHandler) {
    this.logger = Objects.requireNonNull(logger);
    this.transactionHandler = Objects.requireNonNull(transactionHandler);
  }

  @Override
  public void accept(CreateUserEvent event) {
    logger.log(event.getUUID() + ": user added with command " + event.getCommand());
    var user = event.getUser();
    transactionHandler.startSubscribtion(user);
  }
}
