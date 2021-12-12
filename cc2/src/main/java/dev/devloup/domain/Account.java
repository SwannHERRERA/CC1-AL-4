package dev.devloup.domain;

import java.util.Objects;
import java.util.UUID;

import dev.devloup.application.port.in.PaymentEvent;
import dev.devloup.core.Entity;
import dev.devloup.core.EventBus;

@Entity
public class Account {
  private Money balance;
  private final EventBus<PaymentEvent> transactionsBus;
  private final UUID id;

  private Account(UUID id, Money balance, EventBus<PaymentEvent> transactionsBus) throws IllegalArgumentException {
    this.id = id;
    this.balance = Objects.requireNonNull(balance);
    this.transactionsBus = transactionsBus;
  }

  public static Account of(Money balance, EventBus<PaymentEvent> transactionsBus) {
    var id = UUID.randomUUID();
    return new Account(id, balance, transactionsBus);
  }

  public static Account withUUID(UUID id, Money balance, EventBus<PaymentEvent> transactionsBus) {
    return new Account(id, balance, transactionsBus);
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
      transactionsBus.notifyListeners(event);
      return false;
    }
    dest.balance = dest.balance.plus(moneySend);
    balance = balance.minus(moneySend);
    var event = PaymentEvent.createPaymentEvent(this, dest, moneySend, TransactionStatus.SUCCESSED);
    transactionsBus.notifyListeners(event);
    return true;
  }
}
