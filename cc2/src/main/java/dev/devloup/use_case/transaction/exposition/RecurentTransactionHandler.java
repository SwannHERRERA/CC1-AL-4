package dev.devloup.use_case.transaction.exposition;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.Config;
import dev.devloup.core.EventBus;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Transaction;
import dev.devloup.shared.domain.TransactionStatus;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserStatus;
import dev.devloup.use_case.register.domain.UserRepository;

public final class RecurentTransactionHandler {
  private final UserRepository userRepository;
  private final Account plateformAccount;
  private final EventBus<ApplicationEvent> eventBus;

  public RecurentTransactionHandler(UserRepository userRepository, Account plateformAccount,
      EventBus<ApplicationEvent> eventBus) {
    this.userRepository = Objects.requireNonNull(userRepository);
    this.plateformAccount = Objects.requireNonNull(plateformAccount);
    this.eventBus = Objects.requireNonNull(eventBus);
  }

  public void checkForPayment() {
    List<User> userSubscribtionToBeRenewed = userRepository.listAllNonActiveSubscribtion();
    for (User user : userSubscribtionToBeRenewed) {
      var account = user.getAccount();
      var subscribiton = user.getSubscribtion();
      var transaction = account.sendMoney(Config.MONTHLY_SUBSCRIBTION_PRICE, plateformAccount);
      if (transaction.getStatus() == TransactionStatus.ERROR) {
        user.updateSubscribtion(subscribiton.reject(ZonedDateTime.now()));
      } else {
        user.updateSubscribtion(subscribiton.validate(ZonedDateTime.now()));
      }
      eventBus.notifyListeners(transaction);
    }
  }

  public Transaction startSubscribtion(User user) {
    var userAccount = user.getAccount();
    var userSubscribtion = user.getSubscribtion();
    if (userSubscribtion.getStatus() == UserStatus.VERIFIED) {
      throw new UserAlreadySubscribedException();
    }
    var transaction = userAccount.sendMoney(Config.ENROLLMENT_PRICE, plateformAccount);
    if (transaction.getStatus() == TransactionStatus.SUCCESSED) {
      user.updateSubscribtion(userSubscribtion.validate(ZonedDateTime.now()));
    } else {
      user.updateSubscribtion(userSubscribtion.reject(ZonedDateTime.now()));
    }
    eventBus.notifyListeners(transaction);
    return transaction;
  }
}
