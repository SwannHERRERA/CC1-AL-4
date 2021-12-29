package dev.devloup.use_case.register.exposition;

import java.util.List;

import dev.devloup.use_case.register.application.UserMapper;
import dev.devloup.use_case.register.domain.UserRepository;

public final class ListUserQueryHandler implements ListUserUseCase {
  private final UserMapper userMapper;
  private final UserRepository userRepository;

  public ListUserQueryHandler(UserMapper userMapper, UserRepository userRepository) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
  }

  @Override
  public List<UserResponse> listAll(ListAllUserQuery query) {
    return userMapper.mapUsersToUserResponseList(userRepository.listAll());
  }

  @Override
  public UserResponse get(GetUserByIdQuery query) {
    var user = userRepository.findById(query.getId());
    if (user.isEmpty()) {
      return null;
    }
    return userMapper.mapUserToUserResponse(user.get());
  }

}
