package dev.devloup.use_case.register.exposition;

import java.util.List;

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
  public List<String> abilities;
  @NotBlank(message = "profession may not be blank and should be know by our system")
  public String profession;
  public double longitude;
  public double latitude;
  public double activityRadius;
  public int dailyRate;
}
