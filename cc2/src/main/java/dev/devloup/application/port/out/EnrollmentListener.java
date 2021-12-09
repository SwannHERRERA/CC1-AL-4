package dev.devloup.application.port.out;

import dev.devloup.application.port.in.CreateUserEvent;
import dev.devloup.core.Config;
import dev.devloup.core.Logger;
import dev.devloup.domain.Account;
import dev.devloup.domain.Listener;
import dev.devloup.domain.Money;

public final class EnrollmentListener implements Listener<CreateUserEvent> {
  private final Account reciver;
  private final Logger logger;

  public EnrollmentListener(Account reciver, Logger logger) {
    this.reciver = reciver;
    this.logger = logger;
  }

  @Override
  public void accept(CreateUserEvent event) {
    logger.log("user added with command " + event.getCommand());
    var userAccount = event.getUser().getAccount();
    if (userAccount.sendMoney(Money.of(Config.ENROLLMENT_PRICE), reciver)) {
      event.getUser().validate();
    } else {
      event.getUser().reject();
    }
  }
}
