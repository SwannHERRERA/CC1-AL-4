package dev.devloup;

import java.time.ZonedDateTime;
import java.util.UUID;

import dev.devloup.domain.Event;

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
