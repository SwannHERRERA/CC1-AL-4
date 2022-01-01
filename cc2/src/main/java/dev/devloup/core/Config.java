package dev.devloup.core;

import java.time.Period;

public final class Config {
  public static final int BASE_MONEY = 200;
  public static final int ENROLLMENT_PRICE = 20;
  public static final int MONTHLY_SUBSCRIBTION_PRICE = 20;
  private static final String LOG_FOLDER = "/Users/swannherrera/repos/github.com/CC1-AL-4/log/";
  public static final Period DEFAULT_SUBSCRIBTION_PERIOD = Period.ofMonths(1);

  private Config() {
  }

  public static String getLogFolder() {
    return LOG_FOLDER;
  }
}
