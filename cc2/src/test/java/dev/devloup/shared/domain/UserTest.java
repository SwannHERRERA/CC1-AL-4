package dev.devloup.shared.domain;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

final class UserTest {
  private final UserId id = UserId.of(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"));
  private final String firstName = "Swann";
  private final String lastName = "HERRERA";
  private final String email = "swann@devloup.dev";
  private final int age = 20;
  private final UserSubscribtion subscribtion = UserSubscribtion.newDefaultSubscribtion(id);
  private final Account account = Account.of(Money.ZERO);
  private final ProfessionalAbilites professionalAbilites = ProfessionalAbilites.of(new ArrayList<>(),
      Profession.BUILDER, ActivityPerimeter.of(0, 0, 0.02), DailyRate.of(250));

  @Test
  void test_creation_of_user() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    assertEquals(id, user.getId());
    assertEquals(firstName, user.getFirstName());
    assertEquals(lastName, user.getLastName());
    assertEquals(email, user.getEmail());
    assertEquals(age, user.getAge());
    assertEquals(UserStatus.CURRENTLY_AUDITED, user.getStatus());
  }

  @Test
  void test_creation_of_user_with_non_lowercase_email_should_convert_it_to_lowercase() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    assertEquals(email.toLowerCase(), user.getEmail());
  }

  @Test
  void test_user_creation_with_non_valid_email() {
    String email = "random string";
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    });
  }

  @Test
  void test_user_creation_with_non_valid_name() {
    String lastName = null;
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    });
  }

  @Test
  void test_user_creation_with_non_valid_field() {
    String lastName = null;
    UserId id = null;
    String firstName = null;
    assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    });
  }

  @Test
  void test_error_message_for_userCreation_with_bad_email() {
    String email = "random string";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    });
    String message = exception.getMessage();
    assertTrue(message.contains("invalid email"));
  }

  @Test
  void test_error_message_for_userCreation_with_bad_field() {
    String email = "random string";
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    });
    String message = exception.getMessage();
    assertTrue(message.contains("invalid email"));
  }

  @Test
  void test_user_minor() {
    int age = 0;
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    });
    String message = exception.getMessage();
    assertTrue(message.contains("user should be major"));
  }

  @Test
  void test_validation_engine_when_user_is_correct() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    UserValidatorEngine engine = UserValidatorEngine.getInstance();
    assertNull(engine.getErrorMessage(user));
  }

  @Test
  void test_rejection_user() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    assertEquals(UserStatus.CURRENTLY_AUDITED, user.getStatus());
    user.updateSubscribtion(user.getSubscribtion().reject(ZonedDateTime.now()));
    ;
    assertEquals(UserStatus.REJECTED, user.getStatus());
  }

  @Test
  void test_validation_user() {
    User user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    assertEquals(UserStatus.CURRENTLY_AUDITED, user.getStatus());
    user.updateSubscribtion(user.getSubscribtion().validate(ZonedDateTime.now()));
    assertEquals(UserStatus.VERIFIED, user.getStatus());
  }

  @Test
  void test_professional_abilities_without_abilities() {
    var user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilites);
    var userProfessionalAbilities = user.getProfessionalAbilites();
    assertThat(userProfessionalAbilities.getAbilities()).isEmpty();
    assertThat(userProfessionalAbilities.getProfession()).isEqualTo(Profession.BUILDER);
    assertThat(userProfessionalAbilities.getDailyRate()).isEqualTo(DailyRate.of(250));
    assertThat(userProfessionalAbilities.getActivityPerimeter()).isEqualTo(ActivityPerimeter.of(0, 0, 0.02));
  }

  @Test
  void test_professional_abilities() {
    var ability1 = Ability.of("building with wood");
    var ability2 = Ability.of("building with rock");
    var ability3 = Ability.of("working with electricity");
    var professionalAbilities = ProfessionalAbilites.of(List.of(ability1, ability2, ability3), Profession.BUILDER,
        ActivityPerimeter.of(0, 0, 0.0002), DailyRate.of(300));
    var user = User.of(id, firstName, lastName, email, age, account, subscribtion, professionalAbilities);
    var userProfessionalAbilities = user.getProfessionalAbilites();
    assertThat(userProfessionalAbilities.getAbilities()).hasSize(3);
    assertThat(userProfessionalAbilities.getAbilities()).contains(Ability.of("building with wood"), atIndex(0));
    assertThat(userProfessionalAbilities.getProfession()).isEqualTo(Profession.BUILDER);
    assertThat(userProfessionalAbilities.getDailyRate()).isEqualTo(DailyRate.of(300));
    assertThat(userProfessionalAbilities.getActivityPerimeter())
        .isEqualTo(ActivityPerimeter.of(0, 0, 0.0002));
  }

  @Test
  void test_professional_abilities_with_negative_daily_rate() {
    assertThatThrownBy(() -> {
      ProfessionalAbilites.of(Collections.emptyList(), Profession.BUILDER, ActivityPerimeter.of(0, 0, 0),
          DailyRate.of(-1));
    }).isInstanceOf(IllegalArgumentException.class);
  }

}
