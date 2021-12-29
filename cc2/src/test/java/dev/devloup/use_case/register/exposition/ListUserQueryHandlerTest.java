package dev.devloup.use_case.register.exposition;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.devloup.shared.domain.Account;
import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.User;
import dev.devloup.shared.domain.UserId;
import dev.devloup.shared.domain.UserSubscribtion;
import dev.devloup.shared.infrastructure.InMemoryUserRepository;
import dev.devloup.use_case.register.application.UserMapper;
import dev.devloup.use_case.register.domain.UserRepository;

final class ListUserQueryHandlerTest {
  private final ListUserQueryHandler queryHandler;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final User defaultUser;

  public ListUserQueryHandlerTest() {
    this.userRepository = new InMemoryUserRepository();
    this.userMapper = new UserMapper();
    this.queryHandler = new ListUserQueryHandler(userMapper, userRepository);
    this.defaultUser = User.of(UserId.of(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d")), "firstName",
        "lastName",
        "swann@graines-octets.com", 21, Account.of(Money.ZERO),
        UserSubscribtion.newDefaultSubscribtion());
  }

  @Test
  void get_user_when_is_not_in_db() {
    var query = new GetUserByIdQuery(defaultUser.getId());
    var response = queryHandler.get(query);
    Assertions.assertThat(response).isNull();
  }

  @Test
  void get_single_user_test() {
    // add user in db
    userRepository.add(defaultUser);

    var query = new GetUserByIdQuery(defaultUser.getId());
    var response = queryHandler.get(query);
    Assertions.assertThat(response.age).isEqualTo(defaultUser.getAge());
    Assertions.assertThat(response.email).isEqualTo(defaultUser.getEmail());
    Assertions.assertThat(response.firstName).isEqualTo(defaultUser.getFirstName());
    Assertions.assertThat(response.lastName).isEqualTo(defaultUser.getLastName());
  }

  @Test
  void get_user_when_db_is_empty() {
    var query = new ListAllUserQuery();
    var response = queryHandler.listAll(query);
    Assertions.assertThat(response).isEmpty();

  }

  @Test
  void get_all_user_when_we_have_only_one_user() {
    var userResponse = new UserResponse(defaultUser.getId(), defaultUser.getFirstName(), defaultUser.getLastName(),
        defaultUser.getEmail(), defaultUser.getAge(), defaultUser.getStatus());
    userRepository.add(defaultUser);
    var query = new ListAllUserQuery();
    var response = queryHandler.listAll(query);
    Assertions.assertThat(response).containsOnly(userResponse);
  }

  @Test
  void get_all_user_when_we_have_many_user() {
    var user2 = User.of(UserId.generate(), "firstname2", "lastname2", "email@email.sh", 909,
        Account.of(Money.ZERO), UserSubscribtion.newDefaultSubscribtion());
    var userResponse = userMapper.mapUserToUserResponse(defaultUser);
    var user2Response = userMapper.mapUserToUserResponse(user2);
    userRepository.add(defaultUser);
    userRepository.add(user2);
    var query = new ListAllUserQuery();
    var response = queryHandler.listAll(query);
    Assertions.assertThat(response).containsOnly(userResponse, user2Response);
  }
}
