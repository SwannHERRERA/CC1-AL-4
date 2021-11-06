package esgi.al.cc1.domain.exception;

public final class InvalidEmail extends RuntimeException {
  public InvalidEmail(String email) {
    super("The email " + email + " is not valid");
  }
}
