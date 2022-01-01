package dev.devloup.use_case.transaction.exposition;

import java.util.List;
import java.util.Objects;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.Config;
import dev.devloup.core.EventBus;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.Transaction;
import dev.devloup.shared.domain.TransactionStatus;
import dev.devloup.shared.domain.User;
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

  public void batch() {
    List<User> activeUsers = userRepository.listAllActive();
    // TODO get que ce qui ont leur date de payement passé d'un mois
    // Pour tout les utilisateurs regarder si leur date de payement a passer
    // aujourd'hui, les faire renew leur contrat
  }

  public Transaction startSubscribtion(User user) {
    var userAccount = user.getAccount();
    var userSubscribtion = user.getSubscribtion();
    var transaction = userAccount.sendMoney(Money.of(Config.ENROLLMENT_PRICE), plateformAccount);
    if (transaction.getStatus() == TransactionStatus.SUCCESSED) {
      userSubscribtion.validate();
    } else {
      userSubscribtion.reject();
    }
    eventBus.notifyListeners(transaction);
    return transaction;
  }
}
