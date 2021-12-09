package dev.devloup.application.service;

import java.util.List;

import dev.devloup.application.port.in.CreateUserCommand;
import dev.devloup.application.port.in.CreateUserEvent;
import dev.devloup.application.port.in.CreateUserUseCase;
import dev.devloup.application.port.in.GetUserQuery;
import dev.devloup.application.port.in.ListAllUserQuery;
import dev.devloup.application.port.in.ListUserUseCase;
import dev.devloup.application.port.in.PaymentEvent;
import dev.devloup.application.port.in.UserDTO;
import dev.devloup.application.port.out.UserMapper;
import dev.devloup.domain.Account;
import dev.devloup.domain.EventBus;
import dev.devloup.domain.User;
import dev.devloup.domain.UserId;
import dev.devloup.domain.UserRepository;
import dev.devloup.domain.UserStatus;

public class UserService implements CreateUserUseCase, ListUserUseCase {
  private final UserRepository userRepository;
  private final EventBus<CreateUserEvent> enrollmentBus;
  private final EventBus<PaymentEvent> paymentBus;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, EventBus<CreateUserEvent> enrollmentBus,
      EventBus<PaymentEvent> paymentBus) {
    this.userRepository = userRepository;
    this.enrollmentBus = enrollmentBus;
    this.paymentBus = paymentBus;
    this.userMapper = new UserMapper();
  }

  @Override
  public CreateUserEvent createUser(CreateUserCommand command) throws IllegalArgumentException {
    var account = Account.of(command.startBalance, paymentBus);
    var user = User.of(UserId.generate(), command.firstName, command.lastName, command.email, command.age, account,
        UserStatus.CURRENTLY_AUDITED);
    userRepository.add(user);
    var event = CreateUserEvent.withCommandAndUser(command, user);
    enrollmentBus.notifyListeners(event);
    return event;
  }

  @Override
  public List<UserDTO> listAll(ListAllUserQuery query) {
    return userMapper.mapUsersToUserDTOList(userRepository.listAll());
  }

  @Override
  public UserDTO get(GetUserQuery query) {
    var user = userRepository.findById(query.getId());
    if (user.isEmpty()) {
      return null;
    }
    return userMapper.mapUserToUserDTO(user.get());
  }
}
