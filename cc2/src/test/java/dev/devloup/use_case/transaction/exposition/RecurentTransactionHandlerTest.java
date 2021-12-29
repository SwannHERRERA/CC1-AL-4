package dev.devloup.use_case.transaction.exposition;

import org.junit.jupiter.api.Test;

import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserSubscribtion;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.register.domain.UserRepository;

class RecurentTransactionHandlerTest {
  private final UserRepository userRepo = new InMemoryUserRepository();
  private final RecurentTransactionHandler transactionHandler = new RecurentTransactionHandler(userRepo);
  private final User User1 = User.of(UserId.generate(), "firstname", "lastname", "swann@graines-octets.com", 20,
      Account.of(Money.ZERO), UserSubscribtion.newDefaultSubscribtion());

  @Test
  void test_no_transaction_when_no_user() {

    transactionHandler.batch();

  }

}
