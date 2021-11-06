package esgi.al.cc1.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface Event {
  UUID getId();

  ZonedDateTime getOccurredDate();
}
