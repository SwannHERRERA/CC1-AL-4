package dev.devloup.use_case.register.exposition;

import dev.devloup.core.EventBus;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserSubscribtion;
import dev.devloup.use_case.register.domain.UserRepository;

public final class CreateUserCommandHandler implements CreateUserUseCase {
  private final EventBus<CreateUserEvent> createUserBus;
  private final UserRepository userRepository;

  public CreateUserCommandHandler(EventBus<CreateUserEvent> createUserBus,
      UserRepository userRepository) {
    this.createUserBus = createUserBus;
    this.userRepository = userRepository;
  }

  @Override
  public CreateUserEvent createUser(CreateUserCommand command) throws IllegalArgumentException {
    var account = Account.of(Money.of(command.startBalance));
    var subscribtion = UserSubscribtion.newDefaultSubscribtion();
    var user = User.of(UserId.generate(),
        command.firstName,
        command.lastName,
        command.email,
        command.age,
        account,
        subscribtion);
    userRepository.add(user);
    var event = CreateUserEvent.withCommandAndUser(command, user);
    createUserBus.notifyListeners(event);
    return event;
  }

}
