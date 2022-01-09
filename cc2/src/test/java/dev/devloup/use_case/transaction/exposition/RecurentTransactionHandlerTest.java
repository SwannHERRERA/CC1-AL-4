package dev.devloup.use_case.transaction.exposition;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.Config;
import dev.devloup.core.EventBus;
import dev.devloup.core.Listener;
import dev.devloup.core.SimpleEventBus;
import dev.devloup.dummys.DummyTransactionListener;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.ActivityPerimeter;
import dev.devloup.shared.domain.DailyRate;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.Profession;
import dev.devloup.shared.domain.ProfessionalAbilites;
import dev.devloup.shared.domain.Transaction;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserBuilder;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserStatus;
import dev.devloup.shared.domain.UserSubscribtion;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.register.domain.UserRepository;

class RecurentTransactionHandlerTest {
  private final UserRepository userRepo = new InMemoryUserRepository();
  private final EventBus<ApplicationEvent> bus = new SimpleEventBus<>();
  private final ProfessionalAbilites professionalAbilites = ProfessionalAbilites.of(Collections.emptyList(),
      Profession.ELECTRICIAN, ActivityPerimeter.of(0, 0, 0), DailyRate.of(50));
  private final UserSubscribtion userSubscribtion = UserSubscribtion.of(UserStatus.VERIFIED,
      ZonedDateTime.now().minusMonths(1), Config.DEFAULT_SUBSCRIBTION_PERIOD, ZonedDateTime.now().minusMonths(1),
      UserId.of(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")));
  private final RecurentTransactionHandler transactionHandler = new RecurentTransactionHandler(userRepo,
      Account.of(Money.ZERO), bus);
  private final User user1 = User.of(UserId.of(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")), "firstname",
      "lastname", "swann@graines-octets.com", 20,
      Account.of(Money.ZERO),
      userSubscribtion, professionalAbilites);
  private final Listener<Transaction> listener = spy(new DummyTransactionListener());

  @Test
  void test_no_transaction_when_no_user() {
    bus.registerListener(listener, Transaction.class);
    transactionHandler.checkForPayment();
    verify(listener, never()).accept(any());
  }

  @Test
  void test_transaction_when_one_user_needed() {
    bus.registerListener(listener, Transaction.class);
    userRepo.add(user1);
    transactionHandler.checkForPayment();
    verify(listener, times(1)).accept(any());
  }

  @Test
  void test_transaction_when_many_user_needed() {
    bus.registerListener(listener, Transaction.class);

    var user2 = UserBuilder.of(UserId.of(UUID.fromString("c034f49-376f-40dc-8bed-45c3c46f866a")))
        .withFirstname("Swann")
        .withLastname("HERRERA")
        .withEmail("swann.herrera@gmail.com")
        .withAge(21)
        .withProfessionalAbilites(professionalAbilites)
        .withInitalBalance(Money.ZERO)
        .withSubscribtion(UserSubscribtion.of(UserStatus.VERIFIED,
            ZonedDateTime.now().minusMonths(1), Config.DEFAULT_SUBSCRIBTION_PERIOD, ZonedDateTime.now().minusMonths(1),
            UserId.of(UUID.fromString("c034f49-376f-40dc-8bed-45c3c46f866a"))))
        .build();
    userRepo.add(user1);
    userRepo.add(user2);
    transactionHandler.checkForPayment();
    verify(listener, times(2)).accept(any());
  }

  @Test
  void test_start_subscribtion_valid() {
    bus.registerListener(listener, Transaction.class);
    var user = UserBuilder.of(UserId.of(UUID.fromString("c034f49-376f-40dc-8bed-45c3c46f866a")))
        .withFirstname("Swann")
        .withLastname("HERRERA")
        .withEmail("swann.herrera@gmail.com")
        .withAge(21)
        .withProfessionalAbilites(professionalAbilites)
        .withInitalBalance(Money.of(500))
        .withSubscribtion(UserSubscribtion.of(UserStatus.CURRENTLY_AUDITED,
            ZonedDateTime.now().minusMonths(1), Config.DEFAULT_SUBSCRIBTION_PERIOD, ZonedDateTime.now().minusMonths(1),
            UserId.of(UUID.fromString("c034f49-376f-40dc-8bed-45c3c46f866a"))))
        .build();
    var transaction = transactionHandler.startSubscribtion(user);
    verify(listener, times(1)).accept(transaction);
  }

}
