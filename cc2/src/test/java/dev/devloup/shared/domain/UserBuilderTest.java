package dev.devloup.shared.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dev.devloup.core.Config;
import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

public class UserBuilderTest {
  private final UserId userId = UserId.of(UUID.fromString("466e2dc4-7426-4b64-9bd0-acc198a6250d"));
  private final ZonedDateTime now = ZonedDateTime.now();
  private final String lastname = "Herrera";
  private final String firstname = "Swann";
  private final int age = 21;
  private final String email = "swann@graines-octets.com";
  private final Money initalBalance = Money.ZERO;
  private final UserSubscribtion subscribtion = UserSubscribtion.of(UserStatus.VERIFIED, now,
      Config.DEFAULT_SUBSCRIBTION_PERIOD, now, userId);

  @Test
  void test_default_user_construction() {
    var builder = UserBuilder.of(userId);
    builder.withFirstname(firstname)
        .withAge(age)
        .withEmail(email)
        .withLastname(lastname)
        .withInitalBalance(initalBalance)
        .withSubscribtion(subscribtion);
    assertBuildIsCorrect(builder);
  }

  @Test
  void test_with_email_null() {
    assertThatThrownBy(() -> {
      UserBuilder.of(userId)
          .withFirstname(firstname)
          .withAge(age)
          .withEmail(null)
          .withLastname(lastname)
          .withInitalBalance(initalBalance)
          .withSubscribtion(subscribtion)
          .build();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test_with_firstname_null() {
    assertThatThrownBy(() -> {
      UserBuilder.of(userId)
          .withFirstname(null)
          .withAge(age)
          .withEmail(email)
          .withLastname(lastname)
          .withInitalBalance(initalBalance)
          .withSubscribtion(subscribtion)
          .build();
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test_with_negative_amount_of_money() {
    assertThatThrownBy(() -> {
      UserBuilder.of(userId)
          .withFirstname(firstname)
          .withAge(age)
          .withEmail(email)
          .withLastname(lastname)
          .withInitalBalance(-1)
          .withSubscribtion(subscribtion)
          .build();
    }).isInstanceOf(NegativeMoneyAmount.class);
  }

  private void assertBuildIsCorrect(UserBuilder builder) {
    var user = builder.build();
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getFirstName()).isEqualTo(firstname);
    assertThat(user.getLastName()).isEqualTo(lastname);
    assertThat(user.getAccount().getBalance()).isEqualTo(initalBalance);
    assertThat(user.getAge()).isEqualTo(age);
    assertThat(user.getId()).isEqualTo(userId);
    assertThat(user.getStatus()).isEqualTo(UserStatus.VERIFIED);
    assertThat(user.getSubscribtion().getBillingFrequency()).isEqualTo(Config.DEFAULT_SUBSCRIBTION_PERIOD);
    assertThat(user.getSubscribtion().getSubscribtionDate()).isEqualTo(now);
    assertThat(user.getSubscribtion().getLastBilling()).isEqualTo(now);
  }

}
