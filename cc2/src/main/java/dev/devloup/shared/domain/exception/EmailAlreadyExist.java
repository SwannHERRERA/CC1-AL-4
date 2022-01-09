package dev.devloup.shared.domain.exception;

public class EmailAlreadyExist extends IllegalArgumentException {
  public EmailAlreadyExist(String email) {
    super("email : " + email + " already exist");
  }
}
