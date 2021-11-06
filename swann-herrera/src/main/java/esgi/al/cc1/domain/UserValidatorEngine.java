package esgi.al.cc1.domain;

import java.util.function.Predicate;

public class UserValidatorEngine implements Predicate<User> {
  private static final UserValidatorEngine INSTANCE = new UserValidatorEngine();

  private UserValidatorEngine() {
  }

  public static UserValidatorEngine getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean test(User user) {
    // TODO Auto-generated method stub
    return false;
  }

  public String getErrorMessage(User user) {
    return null;
  }

}
