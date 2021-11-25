package esgi.al.cc2;

public final class Config {
  public static final int BASE_MONEY = 200;
  public static final int ENROLLMENT_PRICE = 20;
  private static final String LOG_FOLDER = "/home/swann/repos/github.com/cc2-AL-4/log/";

  private Config() {
  }

  public static String getLogFolder() {
    return LOG_FOLDER;
  }
}
