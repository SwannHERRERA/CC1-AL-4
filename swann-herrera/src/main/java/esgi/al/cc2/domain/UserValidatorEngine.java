package esgi.al.cc2.domain;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.validator.routines.EmailValidator;

import esgi.al.cc2.kernel.Engine;

@Engine
final class UserValidatorEngine implements Predicate<User> {
  private static final UserValidatorEngine INSTANCE = new UserValidatorEngine();

  private UserValidatorEngine() {
  }

  public static UserValidatorEngine getInstance() {
    return INSTANCE;
  }

  private final int AGE_OF_MAJORITY = 18;

  @Override
  public boolean test(User user) {
    try {
      Objects.requireNonNull(user.getFirstName());
      Objects.requireNonNull(user.getLastName());
      Objects.requireNonNull(user.getId());
    } catch (Exception e) {
      return false;
    }
    if (user.getAge() < AGE_OF_MAJORITY) {
      return false;
    }

    EmailValidator emailValidator = EmailValidator.getInstance();
    return emailValidator.isValid(user.getEmail());
  }

  public String getErrorMessage(User user) {
    var errorMessage = new StringBuilder();
    var lineSeparator = System.getProperty("line.separator");
    var emailValidator = EmailValidator.getInstance();
    if (test(user)) {
      return null;
    }
    if (user.getAge() < AGE_OF_MAJORITY) {
      errorMessage.append("user should be major, at least " + AGE_OF_MAJORITY);
      errorMessage.append(lineSeparator);
    }
    if (!emailValidator.isValid(user.getEmail())) {
      errorMessage.append("invalid email");
      errorMessage.append(lineSeparator);
    }
    if (user.getId() == null) {
      errorMessage.append("bad id");
      errorMessage.append(lineSeparator);
    }
    if (user.getFirstName() == null) {
      errorMessage.append("bad firstname");
      errorMessage.append(lineSeparator);
    }
    if (user.getLastName() == null) {
      errorMessage.append("bad lastname");
      errorMessage.append(lineSeparator);
    }
    return errorMessage.toString();
  }

}
