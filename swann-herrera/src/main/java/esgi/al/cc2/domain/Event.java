package esgi.al.cc2.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public interface Event {
  UUID getId();

  ZonedDateTime getOccurredDate();
}
