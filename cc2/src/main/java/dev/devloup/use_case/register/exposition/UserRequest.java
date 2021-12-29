package dev.devloup.use_case.register.exposition;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import dev.devloup.shared.domain.UserValidatorEngine;

public final class UserRequest {
  @NotBlank(message = "firstname may not be blank")
  public String firstName;
  @NotBlank(message = "firstname may not be blank")
  public String lastName;
  @Email()
  public String email;
  @Min(UserValidatorEngine.AGE_OF_MAJORITY)
  public int age;
}
