package dev.devloup.shared.domain;

import java.util.List;
import java.util.Objects;

public final class ProfessionalAbilites {
  private final List<Ability> abilities;
  private final Profession profession;
  private final ActivityPerimeter activityPerimeter;
  private final DailyRate dailyRate;

  private ProfessionalAbilites(List<Ability> abilities, Profession profession, ActivityPerimeter activityPerimeter,
      DailyRate dailyRate) {
    this.abilities = Objects.requireNonNull(abilities);
    this.profession = Objects.requireNonNull(profession);
    this.activityPerimeter = Objects.requireNonNull(activityPerimeter);
    this.dailyRate = Objects.requireNonNull(dailyRate);
  }

  public static ProfessionalAbilites of(List<Ability> abilities, Profession profession,
      ActivityPerimeter activityPerimeter,
      DailyRate dailyRate) {
    return new ProfessionalAbilites(abilities, profession, activityPerimeter, dailyRate);
  }

  public List<Ability> getAbilities() {
    return abilities;
  }

  public Profession getProfession() {
    return profession;
  }

  public ActivityPerimeter getActivityPerimeter() {
    return activityPerimeter;
  }

  public DailyRate getDailyRate() {
    return dailyRate;
  }

}
