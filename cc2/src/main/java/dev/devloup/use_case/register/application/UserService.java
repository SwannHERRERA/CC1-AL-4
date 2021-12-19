package dev.devloup.use_case.register.application;

import java.util.List;

import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.EventBus;
import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserStatus;
import dev.devloup.use_case.register.domain.UserRepository;
import dev.devloup.use_case.register.exposition.CreateUserCommand;
import dev.devloup.use_case.register.exposition.CreateUserEvent;
import dev.devloup.use_case.register.exposition.CreateUserUseCase;
import dev.devloup.use_case.register.exposition.GetUserByIdQuery;
import dev.devloup.use_case.register.exposition.ListAllUserQuery;
import dev.devloup.use_case.register.exposition.ListUserUseCase;
import dev.devloup.use_case.register.exposition.UserDTO;

public class UserService implements CreateUserUseCase, ListUserUseCase {
  private final UserRepository userRepository;
  private final EventBus<ApplicationEvent> eventBus;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, EventBus<ApplicationEvent> eventBus, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.eventBus = eventBus;
  }

  @Override
  public CreateUserEvent createUser(CreateUserCommand command) throws IllegalArgumentException {
    var account = Account.of(Money.of(command.startBalance), eventBus);
    var user = User.of(UserId.generate(), command.firstName, command.lastName, command.email, command.age, account,
        UserStatus.CURRENTLY_AUDITED);
    userRepository.add(user);
    var event = CreateUserEvent.withCommandAndUser(command, user);
    eventBus.notifyListeners(event);
    return event;
  }

  @Override
  public List<UserDTO> listAll(ListAllUserQuery query) {
    return userMapper.mapUsersToUserDTOList(userRepository.listAll());
  }

  @Override
  public UserDTO get(GetUserByIdQuery query) {
    var user = userRepository.findById(query.getId());
    if (user.isEmpty()) {
      return null;
    }
    return userMapper.mapUserToUserDTO(user.get());
  }
}
