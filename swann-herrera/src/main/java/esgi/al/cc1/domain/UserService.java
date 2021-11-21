package esgi.al.cc1.domain;

import java.util.UUID;

import esgi.al.cc1.domain.commands.create_user.CreateUserCommand;
import esgi.al.cc1.domain.commands.create_user.CreateUserUseCase;
import esgi.al.cc1.kernel.Service;

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
  public boolean createUser(CreateUserCommand command) throws IllegalArgumentException {
    var account = Account.of(command.startBalance, paymentBus);
    var user = User.of(UUID.randomUUID(), command.firstName, command.lastName, command.email, command.age, account);
    userRepository.add(user);
    enrollmentBus.notifyListeners(CreateUserEvent.withCommandAndUser(command, user));
    return true;
  }
}
