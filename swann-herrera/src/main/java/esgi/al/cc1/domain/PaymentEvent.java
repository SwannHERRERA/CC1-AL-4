package esgi.al.cc1.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PaymentEvent implements Event {
  private final UUID id;
  private final ZonedDateTime occurrenceDate;
  private final Account source;
  private final Account destination;
  private final TransactionStatus status;

  private PaymentEvent(UUID id, ZonedDateTime occurrenceDate, Account source, Account destination,
      TransactionStatus status) {
    this.id = id;
    this.occurrenceDate = occurrenceDate;
    this.source = source;
    this.destination = destination;
    this.status = status;
  }

  public static PaymentEvent getPaymentEvent(Account source, Account destination, TransactionStatus status) {
    var id = UUID.randomUUID();
    var occurrenceDate = ZonedDateTime.now();
    return new PaymentEvent(id, occurrenceDate, source, destination, status);
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

}
