package dev.devloup.use_case.transaction.exposition;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.EventBus;
import dev.devloup.core.Listener;
import dev.devloup.core.SimpleEventBus;
import dev.devloup.dummys.DummyTransactionListener;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.Transaction;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserSubscribtion;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.register.domain.UserRepository;

class RecurentTransactionHandlerTest {
  private final UserRepository userRepo = new InMemoryUserRepository();
  private final EventBus<ApplicationEvent> bus = new SimpleEventBus<>();
  private final RecurentTransactionHandler transactionHandler = new RecurentTransactionHandler(userRepo,
      Account.of(Money.ZERO), bus);
  private final User user1 = User.of(UserId.generate(), "firstname", "lastname", "swann@graines-octets.com", 20,
      Account.of(Money.ZERO), UserSubscribtion.newDefaultSubscribtion());
  private final Listener<Transaction> listener = Mockito.spy(new DummyTransactionListener());

  @Test
  void test_no_transaction_when_no_user() {

    transactionHandler.batch();

  }

  @Test
  void test_start_subscribtion_valid() {
    bus.registerListener(listener, Transaction.class);
    var transaction = transactionHandler.startSubscribtion(user1);
    verify(listener, times(1)).accept(transaction);
  }

}
