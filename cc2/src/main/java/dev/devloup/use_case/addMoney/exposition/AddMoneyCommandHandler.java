package dev.devloup.use_case.addMoney.exposition;

import java.util.Objects;

import dev.devloup.use_case.register.application.UserMapper;
import dev.devloup.use_case.register.domain.UserRepository;

public final class AddMoneyCommandHandler {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public AddMoneyCommandHandler(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = Objects.requireNonNull(userRepository);
    this.userMapper = Objects.requireNonNull(userMapper);
  }

  public AddMoneyResponse addMoney(AddMoneyCommand command) {
    var optionalUser = userRepository.findById(command.userId);
    if (optionalUser.isEmpty()) {
      throw new IllegalArgumentException("User not found");
    }
    var user = optionalUser.get();
    var account = user.getAccount();
    var transaction = account.addFound(command.amount);
    var userResponse = userMapper.mapUserToUserResponse(user);
    return new AddMoneyResponse(transaction, userResponse, account.getBalance());
  }

}
