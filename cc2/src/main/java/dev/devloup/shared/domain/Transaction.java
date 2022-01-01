package dev.devloup.shared.domain;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Transaction {
  private final Account reciver;
  private final Account sender;
  private final Money amount;
  private final ZonedDateTime occurenceDate;
  private final TransactionId id;
  private final TransactionStatus status;

  @Override
  public int hashCode() {
    return Objects.hash(reciver, sender, amount, occurenceDate, id, status);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    Transaction other = (Transaction) obj;
    if (!id.equals(other.id) || !occurenceDate.equals(other.occurenceDate) || !reciver.equals(other.reciver)
        || !sender.equals(other.sender) || status != other.status) {
      return false;
    }
    return true;
  }

  public Account getReciver() {
    return reciver;
  }

  public Account getSender() {
    return sender;
  }

  public Money getAmount() {
    return amount;
  }

  public ZonedDateTime getOccurenceDate() {
    return occurenceDate;
  }

  public TransactionId getId() {
    return id;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  private Transaction(Account reciver, Account sender, Money amount, ZonedDateTime occurenceDate, TransactionId id,
      TransactionStatus status) {
    this.reciver = Objects.requireNonNull(reciver);
    this.sender = Objects.requireNonNull(sender);
    this.amount = Objects.requireNonNull(amount);
    this.occurenceDate = Objects.requireNonNull(occurenceDate);
    this.id = Objects.requireNonNull(id);
    this.status = Objects.requireNonNull(status);
  }

  public static Transaction create(Account reciver, Account sender, Money amount, TransactionStatus status) {
    return new Transaction(reciver, sender, amount, ZonedDateTime.now(), TransactionId.generate(), status);
  }
}
