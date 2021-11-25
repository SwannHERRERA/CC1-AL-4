package esgi.al.cc2.domain;

import java.util.UUID;

import esgi.al.cc2.domain.commands.create_user.CreateUserCommand;
import esgi.al.cc2.domain.commands.create_user.CreateUserUseCase;
import esgi.al.cc2.kernel.Service;

@Service
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
