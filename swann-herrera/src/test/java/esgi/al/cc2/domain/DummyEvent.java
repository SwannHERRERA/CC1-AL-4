package esgi.al.cc2.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public class DummyEvent implements Event {

  @Override
  public UUID getId() {
    return null;
  }

  @Override
  public ZonedDateTime getOccurredDate() {
    return null;
  }

}
