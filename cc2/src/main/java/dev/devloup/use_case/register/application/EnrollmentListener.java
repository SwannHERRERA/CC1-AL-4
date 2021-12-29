package dev.devloup.use_case.register.application;

import dev.devloup.core.ApplicationEventListener;
import dev.devloup.core.Config;
import dev.devloup.core.Logger;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.use_case.register.exposition.CreateUserEvent;

public final class EnrollmentListener implements ApplicationEventListener<CreateUserEvent> {
  private final Account reciver;
  private final Logger logger;

  public EnrollmentListener(Account reciver, Logger logger) {
    this.reciver = reciver;
    this.logger = logger;
  }

  @Override
  public void accept(CreateUserEvent event) {
    logger.log("user added with command " + event.getCommand());
    var user = event.getUser();
    var userAccount = user.getAccount();
    var userSubscribtion = user.getSubscribtion();
    if (userAccount.sendMoney(Money.of(Config.ENROLLMENT_PRICE), reciver)) {
      userSubscribtion.validate();
    } else {
      userSubscribtion.reject();
    }
  }
}
