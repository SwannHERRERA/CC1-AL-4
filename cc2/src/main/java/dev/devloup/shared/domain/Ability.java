package dev.devloup.shared.domain;

import java.util.Objects;

public final class Ability {
  private final String ability;

  private Ability(String ability) {
    this.ability = Objects.requireNonNull(ability);
  }

  public static Ability of(String ability) {
    return new Ability(ability);
  }

  public String getAbility() {
    return ability;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ability);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Ability other = (Ability) obj;
    return ability.equals(other.ability);
  }

}
