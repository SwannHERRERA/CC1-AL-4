package dev.devloup;

import java.time.ZonedDateTime;
import java.util.UUID;

import dev.devloup.core.ApplicationEvent;

public class DummyEvent implements ApplicationEvent {

  @Override
  public UUID getId() {
    return null;
  }

  @Override
  public ZonedDateTime getOccurredDate() {
    return null;
  }

}
