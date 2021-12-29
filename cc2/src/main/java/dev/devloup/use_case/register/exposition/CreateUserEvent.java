package dev.devloup.use_case.register.exposition;

import java.time.ZonedDateTime;
import java.util.UUID;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.shared.domain.User;

public final class CreateUserEvent implements ApplicationEvent {
  private final UUID id;
  private final CreateUserCommand command;
  private final User user;
  private final ZonedDateTime occurrenceDate;

  private CreateUserEvent(UUID id, ZonedDateTime occurrenceDate, CreateUserCommand command, User user) {
    this.id = id;
    this.occurrenceDate = occurrenceDate;
    this.user = user;
    this.command = command;
  }

  public static CreateUserEvent withCommandAndUser(CreateUserCommand command, User user) {
    return new CreateUserEvent(UUID.randomUUID(), ZonedDateTime.now(), command, user);
  }

  public CreateUserCommand getCommand() {
    return command;
  }

  public User getUser() {
    return user;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public ZonedDateTime getOccurredDate() {
    return occurrenceDate;
  }

}
