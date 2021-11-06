package esgi.al.cc1.domain;

import java.util.UUID;

import esgi.al.cc1.commands.create_user.CreateUserCommand;
import esgi.al.cc1.commands.create_user.CreateUserUseCase;

public class UserService implements CreateUserUseCase {
  final UserRepository userRepository;
  final EventBus<CreateUserEvent> eventBus;

  public UserService(UserRepository userRepository, EventBus<CreateUserEvent> eventBus) {
    this.userRepository = userRepository;
    this.eventBus = eventBus;
  }

  @Override
  public boolean createUser(CreateUserCommand command) {
    User user = User.of(UUID.randomUUID(), command.firstName, command.lastName, command.email, command.age);
    userRepository.add(user);
    eventBus.notifyListeners(CreateUserEvent.withCommandAndUser(command, user));
    return true;
  }
}
