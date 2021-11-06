package esgi.al.cc1.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import esgi.al.cc1.commands.create_user.CreateUserCommand;

public class CreateUserEvent implements Event {
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
