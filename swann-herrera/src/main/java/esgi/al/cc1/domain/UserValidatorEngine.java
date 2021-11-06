package esgi.al.cc1.domain;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.validator.routines.EmailValidator;

public class UserValidatorEngine implements Predicate<User> {
  private static final UserValidatorEngine INSTANCE = new UserValidatorEngine();

  private UserValidatorEngine() {
  }

  public static UserValidatorEngine getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean test(User user) {
    try {
      Objects.requireNonNull(user.getFirstName());
      Objects.requireNonNull(user.getLastName());
      Objects.requireNonNull(user.getId());
    } catch (Exception e) {
      return false;
    }
    EmailValidator emailValidator = EmailValidator.getInstance();
    return emailValidator.isValid(user.getEmail());
  }

  public String getErrorMessage(User user) {
    return null;
  }

}
