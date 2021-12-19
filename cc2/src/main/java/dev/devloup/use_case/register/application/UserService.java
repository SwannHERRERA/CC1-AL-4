package dev.devloup.application.service;

import java.util.List;

import dev.devloup.application.port.in.CreateUserCommand;
import dev.devloup.application.port.in.CreateUserEvent;
import dev.devloup.application.port.in.CreateUserUseCase;
import dev.devloup.application.port.in.GetUserByIdQuery;
import dev.devloup.application.port.in.ListAllUserQuery;
import dev.devloup.application.port.in.ListUserUseCase;
import dev.devloup.application.port.in.UserDTO;
import dev.devloup.application.port.out.UserMapper;
import dev.devloup.core.ApplicationEvent;
import dev.devloup.core.EventBus;
import dev.devloup.domain.Account;
import dev.devloup.domain.User;
import dev.devloup.domain.UserId;
import dev.devloup.domain.UserRepository;
import dev.devloup.domain.UserStatus;

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
    var account = Account.of(command.startBalance, eventBus);
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
