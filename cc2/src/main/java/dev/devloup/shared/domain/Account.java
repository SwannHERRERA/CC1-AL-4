package dev.devloup.shared.domain;

import java.util.Objects;
import java.util.UUID;

import dev.devloup.core.Entity;

@Entity
public class Account {
  private Money balance;
  private final UUID id;

  private Account(UUID id, Money balance) throws IllegalArgumentException {
    this.id = id;
    this.balance = Objects.requireNonNull(balance);
  }

  public static Account of(Money balance) {
    var id = UUID.randomUUID();
    return new Account(id, balance);
  }

  public static Account withUUID(UUID id, Money balance) {
    return new Account(id, balance);
  }

  public Money getBalance() {
    return balance;
  }

  public UUID getId() {
    return id;
  }

  public boolean sendMoney(Money moneySend, Account dest) {
    if (!balance.isGreaterThanOrEqualTo(moneySend)) {
      return false;
    }
    dest.balance = dest.balance.plus(moneySend);
    balance = balance.minus(moneySend);
    return true;
  }
}
