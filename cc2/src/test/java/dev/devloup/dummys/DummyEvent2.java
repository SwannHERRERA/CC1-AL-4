package dev.devloup.dummys;

import java.time.ZonedDateTime;
import java.util.UUID;

import dev.devloup.core.ApplicationEvent;

public class DummyEvent2 implements ApplicationEvent {

  @Override
  public UUID getId() {
    return null;
  }

  @Override
  public ZonedDateTime getOccurredDate() {
    return null;
  }

}
