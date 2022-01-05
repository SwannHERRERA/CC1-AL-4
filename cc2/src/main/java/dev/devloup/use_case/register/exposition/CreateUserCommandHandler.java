package dev.devloup.use_case.register.exposition;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.EventBus;
import dev.devloup.shared.domain.UserBuilder;
import dev.devloup.shared.domain.UserId;
import dev.devloup.use_case.register.domain.UserRepository;

public final class CreateUserCommandHandler implements CreateUserUseCase {
  private final EventBus<ApplicationEvent> createUserBus;
  private final UserRepository userRepository;

  public CreateUserCommandHandler(EventBus<ApplicationEvent> createUserBus,
      UserRepository userRepository) {
    this.createUserBus = createUserBus;
    this.userRepository = userRepository;
  }

  @Override
  public CreateUserEvent createUser(CreateUserCommand command) throws IllegalArgumentException {
    var user = UserBuilder.of(UserId.generate())
        .withFirstname(command.firstName)
        .withLastname(command.lastName)
        .withEmail(command.email)
        .withAge(command.age)
        .withDefaultSubscrbtion()
        .withInitalBalance(command.startBalance)
        .withAbilitiesString(command.abilities)
        .withActivityPerimeter(command.longitude, command.latitude, command.activityRadius)
        .withDailyRate(command.dailyRate)
        .withProfession(command.profession)
        .build();

    userRepository.add(user);
    var event = CreateUserEvent.withCommandAndUser(command, user);
    createUserBus.notifyListeners(event);
    return event;
  }

}
