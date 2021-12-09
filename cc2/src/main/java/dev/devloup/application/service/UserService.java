package dev.devloup.application.service;

import java.util.UUID;

import dev.devloup.application.port.in.CreateUserCommand;
import dev.devloup.application.port.in.CreateUserEvent;
import dev.devloup.application.port.in.CreateUserUseCase;
import dev.devloup.application.port.in.PaymentEvent;
import dev.devloup.domain.Account;
import dev.devloup.domain.EventBus;
import dev.devloup.domain.User;
import dev.devloup.domain.UserRepository;
import dev.devloup.domain.UserStatus;

public class UserService implements CreateUserUseCase {
  private final UserRepository userRepository;
  private final EventBus<CreateUserEvent> enrollmentBus;
  private final EventBus<PaymentEvent> paymentBus;

  public UserService(UserRepository userRepository, EventBus<CreateUserEvent> enrollmentBus,
      EventBus<PaymentEvent> paymentBus) {
    this.userRepository = userRepository;
    this.enrollmentBus = enrollmentBus;
    this.paymentBus = paymentBus;
  }

  @Override
  public CreateUserEvent createUser(CreateUserCommand command) throws IllegalArgumentException {
    var account = Account.of(command.startBalance, paymentBus);
    var user = User.of(UUID.randomUUID(), command.firstName, command.lastName, command.email, command.age, account,
        UserStatus.CURRENTLY_AUDITED);
    userRepository.add(user);
    var event = CreateUserEvent.withCommandAndUser(command, user);
    enrollmentBus.notifyListeners(event);
    return event;
  }
}
