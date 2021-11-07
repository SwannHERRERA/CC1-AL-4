package esgi.al.cc1.domain;

import esgi.al.cc1.Config;

public class EnrollmentListener implements Listener<CreateUserEvent> {
  private final Account reciver;

  public EnrollmentListener(Account reciver) {
    this.reciver = reciver;
  }

  @Override
  public void accept(CreateUserEvent event) {
    var userAccount = event.getUser().getAccount();
    userAccount.sendMoney(Money.of(Config.ENROLLMENT_PRICE), reciver);
  }

}
