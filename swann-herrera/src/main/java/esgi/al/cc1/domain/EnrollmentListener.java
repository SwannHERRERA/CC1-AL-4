package esgi.al.cc1.domain;

import esgi.al.cc1.Config;

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
    logger.log("user added with command" + event.getCommand());
    userAccount.sendMoney(Money.of(Config.ENROLLMENT_PRICE), reciver);
  }

}
