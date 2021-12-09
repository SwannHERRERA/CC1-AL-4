package dev.devloup.application.port.out;

import java.util.ArrayList;
import java.util.List;

import dev.devloup.application.port.in.UserDTO;
import dev.devloup.domain.User;

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
