package dev.devloup.use_case.register.exposition;

import java.time.ZonedDateTime;
import java.util.UUID;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.TransactionStatus;

public final class PaymentEvent implements ApplicationEvent {
  private final UUID id;
  private final ZonedDateTime occurrenceDate;
  private final Account source;
  private final Account destination;
  private final TransactionStatus status;
  private final Money amount;

  private PaymentEvent(UUID id, ZonedDateTime occurrenceDate, Account source, Account destination, Money amount,
      TransactionStatus status) {
    this.id = id;
    this.occurrenceDate = occurrenceDate;
    this.source = source;
    this.destination = destination;
    this.status = status;
    this.amount = amount;
  }

  public static PaymentEvent createPaymentEvent(Account source, Account destination, Money amount,
      TransactionStatus status) {
    var id = UUID.randomUUID();
    var occurrenceDate = ZonedDateTime.now();
    return new PaymentEvent(id, occurrenceDate, source, destination, amount, status);
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public ZonedDateTime getOccurredDate() {
    return occurrenceDate;
  }

  public Account getDestination() {
    return destination;
  }

  public Account getSource() {
    return source;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public Money getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "PaymentEvent [amount=" + amount + ", destination=" + destination + ", id=" + id + ", occurrenceDate="
        + occurrenceDate + ", source=" + source + ", status=" + status + "]";
  }
}
