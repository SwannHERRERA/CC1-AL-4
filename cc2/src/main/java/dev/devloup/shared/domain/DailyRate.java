package dev.devloup.shared.domain;

import java.util.Objects;

public class DailyRate {
  private final int dailyRate;

  private DailyRate(int dailyRate) throws IllegalArgumentException {
    this.dailyRate = Objects.requireNonNull(dailyRate);
    if (dailyRate < 0) {
      throw new IllegalArgumentException("Daily rate should be positive");
    }
  }

  public static DailyRate of(int dailyRate) {
    return new DailyRate(dailyRate);
  }

  public int getDailyRate() {
    return dailyRate;
  }

  @Override
  public int hashCode() {
    return Objects.hash(dailyRate);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DailyRate other = (DailyRate) obj;
    return dailyRate == other.dailyRate;
  }
}
