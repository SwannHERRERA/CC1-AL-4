package dev.devloup.use_case.register.application;

import java.util.ArrayList;
import java.util.List;

import dev.devloup.shared.domain.User;
import dev.devloup.use_case.register.exposition.UserResponse;

public final class UserMapper {
  public UserResponse mapUserToUserResponse(User user) {
    return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(),
        user.getStatus());
  }

  public List<UserResponse> mapUsersToUserResponseList(List<User> users) {
    var list = new ArrayList<UserResponse>();
    for (User user : users) {
      list.add(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(),
          user.getStatus()));
    }
    return list;
  }
}
