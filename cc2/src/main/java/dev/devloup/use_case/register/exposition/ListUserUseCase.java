package dev.devloup.use_case.register.exposition;

import java.util.List;

public interface ListUserUseCase {
  public List<UserDTO> listAll(ListAllUserQuery query);

  public UserDTO get(GetUserByIdQuery id);
}
