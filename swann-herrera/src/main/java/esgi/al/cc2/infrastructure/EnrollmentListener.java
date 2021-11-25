package esgi.al.cc2.infrastructure;

import esgi.al.cc2.Config;
import esgi.al.cc2.domain.Account;
import esgi.al.cc2.domain.CreateUserEvent;
import esgi.al.cc2.domain.Listener;
import esgi.al.cc2.domain.Logger;
import esgi.al.cc2.domain.Money;

public final class EnrollmentListener implements Listener<CreateUserEvent> {
  private final Account reciver;
  private final Logger logger;

  public EnrollmentListener(Account reciver, Logger logger) {
    this.reciver = reciver;
    this.logger = logger;
  }

  @Override
  public void accept(CreateUserEvent event) {
    var userAccount = event.getUser().getAccount();
    logger.log("user added with command " + event.getCommand());
    if (userAccount.sendMoney(Money.of(Config.ENROLLMENT_PRICE), reciver)) {
      event.getUser().validate();
    } else {
      event.getUser().reject();
    }
  }

}
