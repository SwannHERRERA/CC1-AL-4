package dev.devloup.shared.domain;

import java.util.Objects;
import java.util.UUID;

import dev.devloup.core.Entity;
import dev.devloup.core.EventBus;
import dev.devloup.use_case.register.exposition.PaymentEvent;

@Entity
public class Account {
  private Money balance;
  private final EventBus<PaymentEvent> eventBus;
  private final UUID id;

  private Account(UUID id, Money balance, EventBus<PaymentEvent> eventBus) throws IllegalArgumentException {
    this.id = id;
    this.balance = Objects.requireNonNull(balance);
    this.eventBus = eventBus;
  }

  public static Account of(Money balance, EventBus<PaymentEvent> eventBus) {
    var id = UUID.randomUUID();
    return new Account(id, balance, eventBus);
  }

  public static Account withUUID(UUID id, Money balance, EventBus<PaymentEvent> eventBus) {
    return new Account(id, balance, eventBus);
  }

  public Money getBalance() {
    return balance;
  }

  public UUID getId() {
    return id;
  }

  public boolean sendMoney(Money moneySend, Account dest) {
    if (!balance.isGreaterThanOrEqualTo(moneySend)) {
      var event = PaymentEvent.createPaymentEvent(this, dest, moneySend, TransactionStatus.ERROR);
      eventBus.notifyListeners(event);
      return false;
    }
    dest.balance = dest.balance.plus(moneySend);
    balance = balance.minus(moneySend);
    var event = PaymentEvent.createPaymentEvent(this, dest, moneySend, TransactionStatus.SUCCESSED);
    eventBus.notifyListeners(event);
    return true;
  }
}
