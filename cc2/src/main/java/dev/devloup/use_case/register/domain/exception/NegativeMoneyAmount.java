package dev.devloup.application.port.out.exception;

public class NegativeMoneyAmount extends IllegalArgumentException {
  public NegativeMoneyAmount(long value) {
    super("Invalid creation of money with negative value : " + value);
  }
}

