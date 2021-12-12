package dev.devloup.core;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface Event {
  UUID getId();

  ZonedDateTime getOccurredDate();

}
