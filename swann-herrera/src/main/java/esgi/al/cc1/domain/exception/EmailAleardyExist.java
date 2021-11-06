package esgi.al.cc1.domain.exception;

public class EmailAleardyExist extends RuntimeException {
  public EmailAleardyExist(String email) {
    super("The email " + email + " aleardy exist");
  }
}
