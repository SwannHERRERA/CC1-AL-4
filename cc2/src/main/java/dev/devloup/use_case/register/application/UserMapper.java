package dev.devloup.use_case.register.application;

import java.util.ArrayList;
import java.util.List;

import dev.devloup.shared.domain.User;
import dev.devloup.use_case.register.exposition.UserDTO;

public class UserMapper {
  public UserDTO mapUserToUserDTO(User user) {
    return UserDTO.of(user);
  }

  public List<UserDTO> mapUsersToUserDTOList(List<User> users) {
    var list = new ArrayList<UserDTO>();
    for (User user : users) {
      list.add(UserDTO.of(user));
    }
    return list;
  }
}
