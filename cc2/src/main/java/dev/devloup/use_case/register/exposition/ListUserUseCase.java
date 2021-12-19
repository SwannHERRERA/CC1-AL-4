package dev.devloup.use_case.register.exposition;

import java.util.List;

public interface ListUserUseCase {
  public List<UserResponse> listAll(ListAllUserQuery query);

  public UserResponse get(GetUserByIdQuery id);
}
