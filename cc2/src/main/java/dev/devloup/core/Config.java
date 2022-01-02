package dev.devloup.core;

import java.time.Period;

import dev.devloup.shared.domain.Money;

public final class Config {
  public static final int BASE_MONEY = 200;
  public static final Money ENROLLMENT_PRICE = Money.of(20);
  public static final Money MONTHLY_SUBSCRIBTION_PRICE = Money.of(20);
  private static final String LOG_FOLDER = "/Users/swannherrera/repos/github.com/CC1-AL-4/log/";
  public static final Period DEFAULT_SUBSCRIBTION_PERIOD = Period.ofMonths(1);

  private Config() {
  }

  public static String getLogFolder() {
    return LOG_FOLDER;
  }
}
