package dev.devloup.application.port.in;

import java.util.List;

public interface ListUserUseCase {
  public List<UserDTO> listAll(ListAllUserQuery query);

  public UserDTO get(GetUserByIdQuery id);
}
