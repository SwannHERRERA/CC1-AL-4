package esgi.al.cc1.infrastructure;

import esgi.al.cc1.Config;
import esgi.al.cc1.domain.Account;
import esgi.al.cc1.domain.CreateUserEvent;
import esgi.al.cc1.domain.Listener;
import esgi.al.cc1.domain.Logger;
import esgi.al.cc1.domain.Money;

public class EnrollmentListener implements Listener<CreateUserEvent> {
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
