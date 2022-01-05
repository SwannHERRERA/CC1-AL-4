package dev.devloup.shared.domain;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;

import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

public final class UserBuilder {
  private final UserId id;
  private String firstname;
  private String lastname;
  private int age;
  private String email;
  private Account account;
  private UserSubscribtion subscrbition;
  private Profession profession;
  private List<Ability> abilities;
  private DailyRate dailyRate;
  private ActivityPerimeter activityPerimeter;

  private UserBuilder(UserId id) {
    this.id = id;
  }

  public static UserBuilder of(UserId id) {
    return new UserBuilder(id);
  }

  public UserBuilder withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserBuilder withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserBuilder withAge(int age) {
    this.age = age;
    return this;
  }

  public UserBuilder withInitalBalance(Money money) {
    this.account = Account.of(money);
    return this;
  }

  public UserBuilder withInitalBalance(long money) throws NegativeMoneyAmount {
    this.account = Account.of(Money.of(money));
    return this;
  }

  public UserBuilder withSubscribtion(UserSubscribtion subscribtion) {
    this.subscrbition = subscribtion;
    return this;
  }

  public UserBuilder withSubscribtion(UserStatus status, ZonedDateTime lastBilling, Period billingFrequency,
      ZonedDateTime subscribtionDate) {
    this.subscrbition = UserSubscribtion.of(status, lastBilling, billingFrequency, subscribtionDate, id);
    return this;
  }

  public UserBuilder withDefaultSubscrbtion() {
    this.subscrbition = UserSubscribtion.newDefaultSubscribtion(id);
    return this;
  }

  public UserBuilder withAbilities(List<Ability> abilities) {
    this.abilities = abilities;
    return this;
  }

  public UserBuilder withAbilitiesString(List<String> abilitiesString) {
    List<Ability> abilities = abilitiesString.stream().map(a -> Ability.of(a)).toList();
    this.abilities = abilities;
    return this;
  }

  public UserBuilder withProfession(Profession profession) {
    this.profession = profession;
    return this;
  }

  public UserBuilder withProfession(String profession) {
    this.profession = Profession.valueOf(profession);
    return this;
  }

  public UserBuilder withActivityPerimeter(double longitude, double latitude, double activityRadius) {
    this.activityPerimeter = ActivityPerimeter.of(longitude, latitude, activityRadius);
    return this;
  }

  public UserBuilder withActivityPerimeter(ActivityPerimeter activityPerimeter) {
    this.activityPerimeter = activityPerimeter;
    return this;
  }

  public UserBuilder withDailyRate(int dailyRate) {
    this.dailyRate = DailyRate.of(dailyRate);
    return this;
  }

  public UserBuilder withDailyRate(DailyRate dailyRate) {
    this.dailyRate = dailyRate;
    return this;
  }

  public UserBuilder withProfessionalAbilites(ProfessionalAbilites professionalAbilites) {
    this.dailyRate = professionalAbilites.getDailyRate();
    this.activityPerimeter = professionalAbilites.getActivityPerimeter();
    this.abilities = professionalAbilites.getAbilities();
    this.profession = professionalAbilites.getProfession();
    return this;
  }

  public User build() {
    var professionalAbilites = ProfessionalAbilites.of(abilities, profession, activityPerimeter, dailyRate);
    return User.of(id, firstname, lastname, email, age, account, subscrbition, professionalAbilites);
  }
}
