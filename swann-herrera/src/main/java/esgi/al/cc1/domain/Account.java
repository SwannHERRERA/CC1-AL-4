package esgi.al.cc1.domain;

import java.util.Objects;
import java.util.UUID;

import esgi.al.cc1.kernel.Entity;

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

  public boolean sendMoney(Money moneySend, Account reciver) {
    if (!balance.isGreaterThanOrEqualTo(moneySend)) {
      var event = PaymentEvent.createPaymentEvent(this, reciver, moneySend, TransactionStatus.ERROR);
      transactionsBus.notifyListeners(event);
      return false;
    }
    reciver.balance = reciver.balance.plus(moneySend);
    var event = PaymentEvent.createPaymentEvent(this, reciver, moneySend, TransactionStatus.SUCCESSED);
    transactionsBus.notifyListeners(event);
    return true;
  }
}
