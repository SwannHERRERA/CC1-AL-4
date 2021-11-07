package esgi.al.cc1.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PaymentEvent implements Event {
  private final UUID id;
  private final ZonedDateTime occurrenceDate;
  private final Account source;
  private final Account destination;

  private PaymentEvent(UUID id, ZonedDateTime occurrenceDate, Account source, Account destination) {
    this.id = id;
    this.occurrenceDate = occurrenceDate;
    this.source = source;
    this.destination = destination;
  }

  public static PaymentEvent getPaymentEvent(Account source, Account destination) {
    var id = UUID.randomUUID();
    var occurrenceDate = ZonedDateTime.now();
    return new PaymentEvent(id, occurrenceDate, source, destination);
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

}
