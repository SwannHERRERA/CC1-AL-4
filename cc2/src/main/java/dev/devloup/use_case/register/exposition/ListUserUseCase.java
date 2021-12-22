package dev.devloup.use_case.register.exposition;

import java.util.List;

public interface ListUserUseCase {
  public UserResponse get(GetUserByIdQuery id);

  public List<UserResponse> listAll(ListAllUserQuery query);
}
